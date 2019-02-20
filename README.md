# lecturacolas
El proyecto **lecturacolas** ha sido creado con la intención de ejecutar un sistema donde unos datos captados por unos sensores serán introducidos en una cola de mensajes y luego serán leidos por una aplicación conectada a dicha cola. La aplicación, tras leer los datos y tratarlos, los guardará en una base de datos. En éste documento se tratarán los diferentes apartados que cubre el proyecto, su uso y su ejecución.

## Cobertura
En este apartado se explica cada parte cubierta en el proyecto: la cola de mensajes, la base de datos y la aplicación.

#### Cola de mensajes: RabbitMQ
RabbitMQ es un broker de mensajes, donde se pueden crear colas y filtros que clasifican los mensajes que llegan.
Dentro de él, deberá existir una cola desde la que la aplicación leerá los mensajes y un *exchange* del tipo *input* donde se mandarán los mensajes.
La creacion del servidor del broker se llevará a cabo mediante un `docker-compose.yml`, contenido en la carpeta `src/main/resources` de la aplicacion, el cual levantará un contenedor docker con una instancia de rabbitMQ configurada. La configuración del servidor será especificada en un fichero `definitons.json` en la misma carpeta, y se le especificará donde buscarla con un fichero `rabbitmq.config` también en la misma carpeta. 
Dentro del fichero JSON se especifica la cola que queramos crear, llamada **cola_mensajes**, el exchange y su tipo, llamado **input_topic**, y el enlace entre los dos, en el cual se indicará el filtro **cassandra.#** indicando la almohadilla que se puede sustituir por 0 o más caracteres.

#### Base de datos: Apache Cassandra
Cassandra es una base de datos NoSQL con modelo de almacenamiento pares *clave-valor*. Sus tablas se deberán crear asociándose a un *keyspace*, bien ya a uno existente o bien a uno creado por el usuario.
Nuestra aplicación leerá de un keyspace concreto, por lo cual necesita ser creado al iniciar Cassandra. Cassandra será levantada dentro de otro contenedor docker en el fichero `docker-compose.yml`. Como en Cassandra no se pueden ejecutar comandos hasta que la base de datos esté completamente levantada, en el fichero `docker-compose.yml` se define un segundo contenedor de cassandra que, al levantarse y esperar a que el anterior termine de levantarse, ejecutará un comando para copiar dentro del contenedor un fichero `init.cql`, que se encuentra en la misma carpeta que el `docker-compose.yml`, que creará el keypsace que se haya definido dentro del fichero.
La tabla será creada por la aplicación al iniciar, siempre y cuando ésta no exista previamente.

#### Aplicacion Java: lecturacolas
Ésta aplicación, creada con spring boot, utilizará éste framework, el de Spring Data con dependencias de Cassandra y Spring AMQP para asociar a la cola de RabbitMQ. 
Poseerá una clase de configuración de RabbitMQ, `rabbitmqConfig.java`, donde se definirá a qué cola conectará y leerá y qué función deberá tratar los datos leídos, así como otra que se encargará de convertir los datos recibidos en datos legibles por la aplicación.
Poseerá otra clase, `Sensor.java` que defina el modelo de datos que se guardará en la base de datos, y otras dos clases, `SensorRepository.java` y `CassandraService.java`, para manejar la persistencia de los datos.
La última clase, `Receiver.java`, se ocupará de convertir los datos obtenidos de la cola en datos del modelo e insertarlos dentro de la base de datos.

## Ejecución del proyecto
Para poner el proyecto en marcha, primero es necesario tener los contenedores de RabbitMQ y Cassandra levantados, pues de lo contrario la aplicación fallaría. Por tanto, dentro de la carpeta `src/main/resources` o donde quiera que se encuentre el fichero `docker-compose.yml` es necesario ejecutar el siguiente comando:

`docker-compose up -d`

**Nota**: si se decide mover el fichero `docker-compose.yml` debe moverse junto a los ficheros de su misma carpeta (a excepción de `application.properties`).

cuando todos los contenedores estén levantados, ejecutamos el jar de la aplicación. Si éste no se ha creado, en la carpeta del proyecto ejecutamos el comando:

`mvn clean install`

Se generará un archivo **lecturacolas-0.0.1-SNAPSHOT.jar** en la carpeta target.
Para ejecutar ese jar, en la carpeta target (o donde se encuentre el archivo) ejecutaremos el comando:

`java -jar lecturacolas-0.0.1-SNAPSHOT.jar`

levantando así la aplicación.

## Uso del proyecto
Para publicar mensajes hay diferentes modos según el lenguaje del emisor. Aquí se comenta el modo de hacer una prueba local.
Una vez todo esté levantado, podemos acceder a la UI de RabbitMQ mediante el enlace http://localhost:15672 y observar el estado del servidor. Para poder mandar un mensaje entramos en **Exchanges**, en la lista seleccionamos **input_topic** y una vez dentro vamos al apartado **Publish message**. En él, se debe especificar:
- **Routing key**: cassandra.# (Sustituyendo # por 0 o más caracteres)
- **Properties**: content_type=application/json
- **Payload**: Texto escrito en formato JSON donde definimos los valores de los atributos de un objeto del modelo.

`{"nombre-de-atributo1":"valor-de-atributo1", "nombre-de-atributo2":"valor-de-atributo2",..."}`

Los nombres de atributo deben coincidir exactamente con el nombre de los atributos de la clase del modelo. Los atributos que se deben indicar son: **nombreSensor**, **humedad**, **temperatura**, **dioxido_carbono**, **monoxido_carbono** (Siendo todos String). El atributo **momentoCreacion** se crea de forma automatica al crear el nuevo objeto del modelo. 
Mínimo, debe indicarse el atributo **nombreSensor**, pues si no, el mensaje será ignorado y no se introducirá en la base de datos. Si no fuese ignorado, produciría una excepción. El resto de valores pueden ignorarse, se introducirían como cadenas vacías.
Una vez escrito el mensaje, se le da a publicar. La aplicación, la cual suponemos que está ejecutando, leerá de la cola los datos, los convertirá a un formato JSON (debido a que la aplicación recibe el mensaje en cadena de bytes), lo enviará a la función receptora, la cual convertirá el mensaje a formato de modelo y, si el nombre del sensor no está vacío, lo introducirá en la base de datos.

Para poder ver la tabla en la base de datos, entramos en su CLI mediante el comando: 

`docker exec -it cassandra_db cqlsh`

y una vez dentro ejecutamos el comando:

`SELECT * FROM cassandra_keyspace.sensor;`

para ver todos los datos que se han introducido dentro de la tabla.
Si fuera necesario, dentro de la aplicación se pueden definir servicios CRUD más allá del guardado de datos.
