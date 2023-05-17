package com.example.Notifications.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Notifications.config.kafka.kafkaListener;

public class KafkaConsumerServiceImpl implements KafkaConsumerService{

	@Autowired
	private kafkaListener kafkaListener;
	
	
	public void TwillioSmsSend () {
		
	}
}
