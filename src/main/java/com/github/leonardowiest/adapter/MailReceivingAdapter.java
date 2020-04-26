package com.github.leonardowiest.adapter;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowBuilder;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.mail.dsl.MailInboundChannelAdapterSpec;
import org.springframework.stereotype.Component;

import com.github.leonardowiest.config.SearchStrategy;

/**
 * 
 * @author Leonardo Wiest
 *
 */
@Component
public class MailReceivingAdapter {

	@Value("${mail.protocol}")
	private String protocol;

	@Value("${mail.username}")
	private String username;

	@Value("${mail.password}")
	private String password;

	@Bean
	public IntegrationFlow mailReadFlow() {

		return getFlowBuilder().get();

		// IntegrationFlows.from(getInboundChannelAdapterConfig()).handle("mailReceiving",
		// "handle").get();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private IntegrationFlowBuilder getFlowBuilder() {

		IntegrationFlowBuilder flowBuilder;

		MailInboundChannelAdapterSpec adapterSpec;

		switch (protocol.toUpperCase()) {
		case "IMAP":
		case "IMAPS":
			adapterSpec = getImapFlowBuilder();
			break;
		case "POP3":
		case "POP3S":
		default:

			throw new IllegalArgumentException(
					String.format("Protocolo de correio informado não suportado: %s", protocol));
		}

		flowBuilder = IntegrationFlows.from(adapterSpec);

		return flowBuilder;
	}

	@SuppressWarnings("rawtypes")
	private MailInboundChannelAdapterSpec getImapFlowBuilder() {

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

	private Properties getProperties() {

		Properties prop = new Properties();

		prop.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.imap.socketFactory.fallback", "false");
		prop.setProperty("mail.store.protocol", "imaps");
		prop.setProperty("mail.debug", "true");

		return prop;
	}

}
