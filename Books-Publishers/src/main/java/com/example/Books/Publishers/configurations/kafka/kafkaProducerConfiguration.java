package com.example.Books.Publishers.configurations.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class kafkaProducerConfiguration {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
	@Value("${spring.kafka.request.timeout.ms}")
	private String kafkaTimeOut;
	
	@Bean
	public Map<String, Object> producerConfig(){
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, kafkaTimeOut);
		return props;
	}
	
	@Bean
	public ProducerFactory<String, String> defaultKafkaProducerConfig(){
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}
	
	@Bean
	public KafkaTemplate<String, String> kafkatemplate(
			ProducerFactory<String, String> defaultKafkaProducerConfig
			)
	{
		return new KafkaTemplate<>(defaultKafkaProducerConfig);
	}
}
