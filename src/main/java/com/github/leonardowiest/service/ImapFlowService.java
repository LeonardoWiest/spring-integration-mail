package com.github.leonardowiest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.mail.dsl.MailInboundChannelAdapterSpec;
import org.springframework.stereotype.Service;

import com.github.leonardowiest.config.SearchStrategy;
import com.github.leonardowiest.properties.MailProperties;

@Service
public class ImapFlowService {

	@Autowired
	private MailProperties mailProperties;

	public MailInboundChannelAdapterSpec<?, ?> getImapFlow() {

		ImapMailReceiver receiver = new ImapMailReceiver(getStoreURI());

		receiver.setShouldMarkMessagesAsRead(true);
		receiver.setShouldDeleteMessages(false);
		receiver.setJavaMailProperties(mailProperties.getProperties());
		receiver.setSearchTermStrategy(new SearchStrategy());

		return Mail.imapInboundAdapter(receiver);
	}

	private String getStoreURI() {

		String sanitizedUsername = sanitizeUsername();

		return String.format("imaps://%s:%s@imap.gmail.com/INBOX", sanitizedUsername, mailProperties.getPassword());
	}

	private String sanitizeUsername() {

		return mailProperties.getUsername().replace("@", "%40");
	}
}
