package com.proyectoat.lecturacolas.config;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JMSConfig {

	
		@Value("${active-mq-url}")
		private String activeMqUrl;

		@Value("${active-mq-username}")
		private String activeMqUsername;
		
		@Value("${active-mq-password}")
		private String activeMqPassword;
		
		@Bean
		public Queue queue() {
			return new ActiveMQQueue("cola_mensajes");
		}
		
		@Bean
		public ActiveMQConnectionFactory connectionFatory(){
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
			factory.setBrokerURL(activeMqUrl);
			factory.setUserName(activeMqUsername);
			factory.setPassword(activeMqPassword);
			return factory;
		}
		
		@Bean
		public JmsTemplate jmsTemplate(){
				return new JmsTemplate(connectionFatory());
		} 


	
	
}
