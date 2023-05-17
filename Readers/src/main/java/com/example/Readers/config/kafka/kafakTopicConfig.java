package com.example.Readers.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafakTopicConfig {

	
	@Value("${spring.kafka.template.default-topic}")
	private String defaultTopic;
	
	@Bean
	public NewTopic testTopic (){
 		return TopicBuilder.name(defaultTopic).build();
	}
}
