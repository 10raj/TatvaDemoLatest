package com.example.Staff.config.kafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisher {

	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${spring.kafka.template.default-topic}")
	private String defaultTopic;

	public void sendMessage(String msg) {
	    kafkaTemplate.send(defaultTopic, msg);
	} 
	
//	@Bean
//	public void sending() {
//		sendMessage("RajTesting book micro services" );
//	}
}
