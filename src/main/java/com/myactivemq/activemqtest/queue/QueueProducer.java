package com.myactivemq.activemqtest.queue;

import javax.jms.JMSException;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.myactivemq.activemqtest.entity.Employee;

@Component
public class QueueProducer {
	static Logger log = LoggerFactory.getLogger(QueueProducer.class);

	@Autowired
	private Queue queue;

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(Employee employee) throws JMSException {
		jmsTemplate.convertAndSend(queue, employee);
		log.info("QueueProducer:sendMessage(): Message has been put to queue by sender:"+queue.getQueueName());
	}
}
