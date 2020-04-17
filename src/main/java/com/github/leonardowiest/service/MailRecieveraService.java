package com.github.leonardowiest.service;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class MailRecieveraService {

	@Bean
	public static MessageHandler processNewEmail() {

		MessageHandler messageHandler = new MessageHandler() {

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				// TODO Auto-generated method stub

			}
		};

		return messageHandler;
	}
}
