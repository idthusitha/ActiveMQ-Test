package com.myactivemq.activemqtest.queue;

import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.myactivemq.activemqtest.entity.Employee;

@Component
@EnableJms
public class QueueConsumer {

	static Logger log = LoggerFactory.getLogger(QueueConsumer.class);

	@JmsListener(destination = "${active-mq.queue.name}")
	public void receiveQueue(Message message) {
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			Employee employee = (Employee) objectMessage.getObject();

			try {
				Thread.sleep(2000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("QueueConsumer: receiveQueue(): Message Received: " + employee.toString());
		} catch (Exception e) {
			log.error("Error", e);
		}

	}
}
