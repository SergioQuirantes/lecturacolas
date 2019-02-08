# lecturacolas
La aplicación creada con Spring Boot y las dependencias de Spring Data con Cassandra y las de RabbitMQ. Se encarga automáticamente de recibir los mensajes de la cola de mensajes cuando los hay, tratar los datos e insertarlos dentro de una tabla en la BD, la cual crea si no existe ya. Tiene también implementados otros servicios para leer, actualizar y borrar datos.

# docker-compose.yml
El fichero que se encuentra en src/main/resources se ocupa de cargar y levantar los contenedores con el broker de mensajes (RabbitMQ) y 
la base de datos (cassandra). Se ejecuta con el comando:
  
  `docker-compose up -d`

La opción `-d` sirve para ejecutarse como daemon.

# RabbitMQ
El broker de mensajes que hemos levantado. Se accede a su UI con `localhost:15672`. Al levantarse se crea su **cola de mensajes**, llamada sencillamente `cola_mensajes` durante la prueba, y un **topic**, llamado `input_topic`.

Se crea también un binding entre ambos cuya clave de ruta es `cassandra.#`, indicando la almohadilla que se puede sustituir por 0 o más palabras. Se utiliza como filtro de mensajes. La aplicación también esperará que el contenido del mensaje esté en formato json.

# Cassandra
La base de datos NoSQL que se ha levantado. Al no poseer UI, cualquier consulta independiente a la aplicación se deberá hacer por linea de comandos. Una vez levantado, un segundo contenedor en docker ejecuta un script para crear la configuración inicial de cassandra.

En ésta base de datos se irán almacenando los datos obtenidos de la cola de mensajes por la aplicación.
