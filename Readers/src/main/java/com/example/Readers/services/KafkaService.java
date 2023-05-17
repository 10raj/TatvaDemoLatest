package com.example.Readers.services;

import java.util.concurrent.Executors;

import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Readers.config.kafka.KafkaPublisher;

@Component
public class KafkaService {
	
	@Autowired
	private KafkaPublisher kafkaPublisher;


	public void sendMessageToKafka(String kafkaMessage) {
		try {
			Executors.newSingleThreadExecutor().execute(new Runnable() {
	            @Override
	            public void run() {
	            	kafkaPublisher.sendMessage(kafkaMessage);;
	            }
	        });
		}
		catch(Exception e) {
			throw new KafkaException("not able to send SMS");
		}
	}
}
