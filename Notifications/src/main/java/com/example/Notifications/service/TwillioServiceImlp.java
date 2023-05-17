package com.example.Notifications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Notifications.config.twillio.TwillioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber; 

@Service
public class TwillioServiceImlp implements TwillioService{

	@Autowired
	private TwillioConfig twillioConfig;
	
	public void sendSms(String message, String toNumber) {
		Twilio.init(twillioConfig.getACCOUNT_SID(), twillioConfig.getAUTH_TOKEN());
		Message.creator(
			    new PhoneNumber("+918200821957"),
			    new PhoneNumber(twillioConfig.getFromNumber()),
			    message)
			.create();
	}
	
}
