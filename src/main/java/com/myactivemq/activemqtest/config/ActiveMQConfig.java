package com.myactivemq.activemqtest.config;

import java.util.Arrays;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.broker.region.policy.RedeliveryPolicyMap;
import org.apache.activemq.command.ActiveMQQueue;
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
	
	@Value("${spring.activemq.user}")
	private String user;
	
	@Value("${spring.activemq.password}")
	private String password;
	
	@Value("${active-mq.queue.name}")
	private String queueName;

	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		activeMQConnectionFactory.setUserName(user);
		activeMQConnectionFactory.setPassword(password);	
		
		activeMQConnectionFactory.setRedeliveryPolicy(getRedeliveryPolicy());
		
		//This is for the consumer
		activeMQConnectionFactory.setTrustedPackages(Arrays.asList("com.myactivemq.activemqtest.entity"));
		logger.info("ActiveMQConfig, connectionFactory(): " + brokerUrl);
		return activeMQConnectionFactory;
	}

	protected RedeliveryPolicy getRedeliveryPolicy() {
        RedeliveryPolicy policy = new RedeliveryPolicy();
        policy.setInitialRedeliveryDelay(10);
        policy.setRedeliveryDelay(1000);
        policy.setMaximumRedeliveries(3);
        policy.setMaximumRedeliveryDelay(1000);
        policy.setBackOffMultiplier((short)2);
        policy.setUseExponentialBackOff(true);
        return policy;
    }

	@Bean
	public JmsTemplate jmsTemplate() {
		logger.info("ActiveMQConfig, jmsTemplate()");
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		//jmsTemplate.setPubSubDomain(true); // enable for Pub Sub to topic. Not Required for Queue.
		return jmsTemplate;
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		
		/**This is needed for true in TOPIC*/
		factory.setPubSubDomain(false);
		
		//factory.setConcurrency("1-1");
		return factory;
	}	
	
	@Bean
    public Queue queue(){
        return new ActiveMQQueue(queueName);
    }
}
