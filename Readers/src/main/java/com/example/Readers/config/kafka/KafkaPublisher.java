package com.example.Readers.config.kafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaPublisher {

	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${spring.kafka.template.default-topic}")
	private String defaultTopic;

	public void sendMessage(String msg) {
		log.info("message: {} sent  to kafka server",msg);
	    //kafkaTemplate.send(defaultTopic, msg);
	} 
	
	@Bean
	public void sending() {
		sendMessage("RajTesting book micro services" );
	}
}
