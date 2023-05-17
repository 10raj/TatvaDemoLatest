package com.example.Notifications.service;

public interface TwillioService {

	public void sendSms(String message, String toNumber);
}
