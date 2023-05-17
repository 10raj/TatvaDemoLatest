package com.example.AppGateWay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@EnableDiscoveryClient
public class AppGateWayApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(AppGateWayApplication.class, args);
		
	}
}
