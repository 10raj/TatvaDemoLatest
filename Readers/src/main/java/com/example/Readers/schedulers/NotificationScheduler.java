package com.example.Readers.schedulers;

import java.util.Arrays;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.Readers.config.kafka.KafkaPublisher;
import com.example.Readers.entity.Readers;
import com.example.Readers.repository.ReadersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@EnableAsync
@Component
@Slf4j
public class NotificationScheduler {

	private final KafkaPublisher kafkaPublisher;
	private final RestTemplate restTemplate;
	private final ReadersRepository readersRepository;
	
	@Async
	@Scheduled(fixedDelay = 1000*60*60*24, initialDelay = 1000*60)
	public void dueDateWarningNotification() {
		String uri="http://localhost:9999/book/readerdueList";
		Long[] readersIds=restTemplate.getForObject(uri,Long[].class);
		List<Long> readersIdList = Arrays.asList(readersIds);
		readersIdList.forEach((id)->sendMsg(id));;
	}
	
	private void sendMsg(Long id) {
		Readers reader = readersRepository.findById(id).get();
		String message="Mr."+reader.getName()+"your book due date has passed please return as soon as possible ";
		kafkaPublisher.sendMessage(message);
		log.info("Message:{} has been sent",message);
	}
}
