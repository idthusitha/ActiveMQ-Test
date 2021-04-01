package com.myactivemq.activemqtest.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myactivemq.activemqtest.entity.Employee;
import com.myactivemq.activemqtest.message.JmsProducer;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProduceMessageController {

	static Logger logger = LoggerFactory.getLogger(ProduceMessageController.class);

	@Autowired
	JmsProducer jmsProducer;

	@ApiOperation(value = "sendMessage")
	@PostMapping(value = "/api/employee")
	public ResponseEntity<?> sendMessage(@RequestBody Employee employee) {
		logger.info("/api/employee, start.....................");
		jmsProducer.sendMessage(employee);

		return ResponseEntity.ok(employee);
	}
	
	@ApiOperation(value = "Service Check link")
	@RequestMapping(value = "/api/servicecheck", method = RequestMethod.GET)
	public String servicecheck() {
		return "This is the First Message From Remote ActiveMQ-Test!";
	}
}