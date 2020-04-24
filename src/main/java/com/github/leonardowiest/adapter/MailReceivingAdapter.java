package com.github.leonardowiest.adapter;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.integration.mail.dsl.ImapMailInboundChannelAdapterSpec;
import org.springframework.integration.mail.dsl.Mail;

public class MailReceivingAdapter {

	@Bean
	public IntegrationFlow mailReadFlow() {

		return IntegrationFlows.from(getInboundChannelAdapterConfig());
	}

	private ImapMailInboundChannelAdapterSpec getInboundChannelAdapterConfig() {

		ImapMailReceiver receiver = new ImapMailReceiver(getStoreURI());
		receiver.setJavaMailProperties(getProperties());
		receiver.setShouldDeleteMessages(false);
		receiver.setShouldMarkMessagesAsRead(false);

		return Mail.imapInboundAdapter(receiver);
	}

	/**
	 * 
	 * No lugar de 'mail' vai o e-mail. Substituir '@' por '%40'.
	 * 
	 * Exemplos:
	 * 
	 * mail: jose123%40gmail.com <br>
	 * password: 123456
	 * 
	 * @return
	 */
	private String getStoreURI() {
		return "imaps://mail:password@imap.gmail.com/INBOX";
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
