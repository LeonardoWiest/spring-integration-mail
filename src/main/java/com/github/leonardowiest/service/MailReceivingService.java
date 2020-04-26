package com.github.leonardowiest.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Leonardo Wiest
 *
 */
@Service(value = "mailReceiving")
public class MailReceivingService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void handle(MimeMessage message) {

		logger.info("Entrou...");
	}
}
