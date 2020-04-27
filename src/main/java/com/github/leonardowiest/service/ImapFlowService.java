package com.github.leonardowiest.service;

import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.mail.dsl.MailInboundChannelAdapterSpec;
import org.springframework.stereotype.Service;

import com.github.leonardowiest.config.SearchStrategy;

@Service
public class ImapFlowService {

	public MailInboundChannelAdapterSpec<?, ?> getImapFlow() {

		ImapMailReceiver receiver = new ImapMailReceiver(getStoreURI());

		receiver.setShouldMarkMessagesAsRead(true);
		receiver.setShouldDeleteMessages(false);
		receiver.setJavaMailProperties(getProperties());
		receiver.setSearchTermStrategy(new SearchStrategy());

		return Mail.imapInboundAdapter(receiver);
	}

	private String getStoreURI() {

		String sanitizedUsername = sanitizeUsername();

		return String.format("imaps://%s:%s@imap.gmail.com/INBOX", sanitizedUsername, password);
	}

	private String sanitizeUsername() {

		return username.replace("@", "%40");
	}
}
