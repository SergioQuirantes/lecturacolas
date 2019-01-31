package com.proyectoat.lecturacolas;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.proyectoat.lecturacolas.model.ModeloPrueba;
import com.proyectoat.lecturacolas.receiver.Receiver;




@SpringBootApplication
public class LecturacolasApplication {

	static final String topicExchangeName = "input_topic";
	
	static final String queueName = "cola_mensajes";
	
	@Bean
	Queue queue() {
		return new Queue(queueName, true);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("cassandra.#");
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, 
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		
		
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		MessageListenerAdapter adapter = new MessageListenerAdapter(receiver, "recibeMensaje");
		adapter.setMessageConverter(getJsonMessageConverter());
		return adapter;
	}
	
	public MessageConverter getJsonMessageConverter() {
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        jackson2JsonMessageConverter.setClassMapper(new ClassMapper() {
			
			@Override
			public Class<?> toClass(MessageProperties properties) {
				// TODO Auto-generated method stub
				return ModeloPrueba.class;
			}
			
			@Override
			public void fromClass(Class<?> clazz, MessageProperties properties) {
				// TODO Auto-generated method stub
				
			}
		});
        
	    return jackson2JsonMessageConverter;
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(LecturacolasApplication.class, args);
	}

}

