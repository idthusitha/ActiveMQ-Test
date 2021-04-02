package com.myactivemq.activemqtest.controller;

import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myactivemq.activemqtest.entity.Employee;
import com.myactivemq.activemqtest.queue.QueueProducer;
import com.myactivemq.activemqtest.topic.TopicProducer;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProduceMessageController {

	static Logger logger = LoggerFactory.getLogger(ProduceMessageController.class);

	@Autowired
	TopicProducer topicProducer;

	@Autowired
	QueueProducer queueProducer;

	@ApiOperation(value = "sendTopicMessage")
	@PostMapping(value = "/api/topic/employee")
	public ResponseEntity<?> sendTopicMessage(@RequestBody Employee employee) {
		logger.info("/api/topic/employee, start.....................");
		topicProducer.sendMessage(employee);

		return ResponseEntity.ok(employee);
	}

	@ApiOperation(value = "sendQueueMessage")
	@PostMapping(value = "/api/queue/employee")
	public ResponseEntity<?> sendQueueMessage(@RequestBody Employee employee) throws JMSException {
		logger.info("/api/queue/employee, start.....................");
		queueProducer.sendMessage(employee);

		return ResponseEntity.ok(employee);
	}

	@ApiOperation(value = "Service Check link")
	@RequestMapping(value = "/api/servicecheck", method = RequestMethod.GET)
	public String servicecheck() {
		return "This is the First Message From Remote ActiveMQ-Test!";
	}
}