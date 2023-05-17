package com.example.Notifications.config.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.Notifications.service.TwillioService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class kafkaListener {

	@Autowired
	TwillioService twillioService;
	
	
	@KafkaListener(
			topics="LM-SMS",
			groupId = "G1"
			)
	public void listeners(String data) {
		log.info("++++++++++++++++++++listner called++++++++++++++++:{}",data);
		twillioService.sendSms(data, null);
	}
	
}

 