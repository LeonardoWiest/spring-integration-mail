package com.github.leonardowiest.service;

import java.io.File;
import java.io.IOException;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
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

	public void handle(MimeMessage message) throws MessagingException, IOException {

		Folder folder = message.getFolder();

		folder.open(Folder.READ_ONLY);

		Message[] messages = folder.getMessages();

		for (int index = 0; index < messages.length; index++) {

			Multipart multiPart = (Multipart) message.getContent();

			Integer numberOfParts = multiPart.getCount();

			for (int partCount = 0; partCount < numberOfParts; partCount++) {

				MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);

				if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {

					String fileName = part.getFileName();

					part.saveFile("C:\\Ferramentas" + File.separator + fileName);
				}
			}
		}

	}
}
