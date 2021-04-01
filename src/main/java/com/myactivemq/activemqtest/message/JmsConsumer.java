package com.myactivemq.activemqtest.message;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.myactivemq.activemqtest.entity.Employee;

@Component
public class JmsConsumer implements MessageListener {
	static Logger log = LoggerFactory.getLogger(MessageListener.class);

	@Override
	@JmsListener(destination = "${active-mq.topic}")
	public void onMessage(Message message) {
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			Employee employee = (Employee) objectMessage.getObject();
			// do additional processing
			try {
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			log.info("Received Message: " + employee.toString());
		} catch (Exception e) {
			log.error("Received Exception : " + e);
		}

	}
}
