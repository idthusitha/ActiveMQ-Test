package com.myactivemq.activemqtest.config;

import java.util.Arrays;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ActiveMQConfig {

	static Logger logger = LoggerFactory.getLogger(ActiveMQConfig.class);
	
	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;

	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		//This is for the consumer
		activeMQConnectionFactory.setTrustedPackages(Arrays.asList("com.myactivemq.activemqtest.entity"));
		logger.info("ActiveMQConfig, connectionFactory(): " + brokerUrl);
		return activeMQConnectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		logger.info("ActiveMQConfig, jmsTemplate()");
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setPubSubDomain(true); // enable for Pub Sub to topic. Not
											// Required for Queue.
		return jmsTemplate;
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setPubSubDomain(true);
		return factory;
	}	
}
