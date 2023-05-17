package com.example.Notifications.config.twillio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwillioConfig {
    public String ACCOUNT_SID;
	public String AUTH_TOKEN;
	public String fromNumber;
	
}
