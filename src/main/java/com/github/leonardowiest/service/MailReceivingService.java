package com.github.leonardowiest.service;

import javax.mail.internet.MimeMessage;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Leonardo Wiest
 *
 */
@Service(value = "meuBean")
public class MailReceivingService {

	@ServiceActivator(inputChannel = "handleRecebimento")
	public void handleRecebimento(MimeMessage message) {

		System.out.println("entrou...");
	}
}
