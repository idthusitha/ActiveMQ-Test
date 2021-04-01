package com.myactivemq.activemqtest.config;


import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

public class ActiveMQConnectionFactory {
	
//	@Bean
//	public ConnectionFactory connectionFactory() {
//		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
//		activeMQConnectionFactory.setBrokerURL(brokerUrl);
//		activeMQConnectionFactory.setTrustedPackages(Arrays.asList("com.mailshine.springbootstandaloneactivemq"));
//		return activeMQConnectionFactory;
//	}
//
//	@Bean
//	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory());
//		factory.setPubSubDomain(true);
//		return factory;
//	}
}
