package com.proyectoat.lecturacolas.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.proyectoat.lecturacolas.receiver.Receiver;

/**
 * Clase de configuracion del Broker
 * 
 * @author squirantes
 *
 */
@Configuration
public class rabbitmqConfig {
	/**
	 * Nombre del topic por donde se insertan los mensajes
	 */
	static final String topicExchangeName = "input_topic";

	/**
	 * Nombre de la cola de mensajes
	 */
	static final String queueName = "cola_mensajes";

	/**
	 * Cola de mensajes donde se escucharán los mensajes
	 * @return Queue
	 */
	@Bean
	Queue queue() {
		return new Queue(queueName, true); //se devuelve una con el nombre queueName y con durable=true
	}

	/**
	 * Exchange por donde se escribirán los mensajes en la cola
	 * @return TopicExchange
	 */
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName); //se devuelve un topic con el nombre topicEchangeName
	}

	
	/**
	 * Enlace entre la cola de mensajes y el exchange de input
	 * @param queue
	 * @param exchange
	 * @return Binding
	 */
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("cassandra.#"); //para que el binding reciba mensajes, un mensaje se debe mandar con esa routing_key
	}

	/**
	 * Container que recibirá un ConnectionFactory y un MessageListenerAdapter por parámetro, ambos del contexto, 
	 * y al que se le asignara el nombre de cola queueName, de donde deberá escuchar los mensajes
	 *
	 * @param connectionFactory
	 * @param listenerAdapter
	 * @return SimpleMessageListenerContainer
	 */
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(); //nuevo contenedor
		container.setConnectionFactory(connectionFactory); //se establece el ConnectionFactory del contexto
		container.setQueueNames(queueName); //se establece el nombre de la cola de donde escuchara
		container.setMessageListener(listenerAdapter); //se establece el listener adapter, el cual definiremos nosotros más abajo en un bean

		return container;
	}

	/**
	 * ListenerAdapter que se llevará al contexto que se usará para tartar la información que llega desde la cola
	 * @param receiver
	 * @return MessageListenerAdapter
	 */
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		MessageListenerAdapter adapter = new MessageListenerAdapter(receiver, "recibeMensaje"); //nuevo Adapter con el componente Receiver y la funcion recibeMensaje
		adapter.setMessageConverter(getJsonMessageConverter()); //se establece como traducirá el mensaje el adaptador
		return adapter;
	}

	/**
	 * Devolverá un conversor de lo recibido de la cola (en cadena de bytes) a JSON
	 * @return MessageConverter
	 */
	public MessageConverter getJsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
