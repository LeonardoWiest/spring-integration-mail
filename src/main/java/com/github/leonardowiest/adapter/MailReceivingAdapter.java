package com.github.leonardowiest.adapter;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.integration.mail.dsl.ImapMailInboundChannelAdapterSpec;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.stereotype.Component;

import com.github.leonardowiest.config.SearchStrategy;

/**
 * 
 * @author Leonardo Wiest
 *
 */
@Component
public class MailReceivingAdapter {

	@Bean
	public IntegrationFlow mailReadFlow() {

		return IntegrationFlows.from(getInboundChannelAdapterConfig()).handle("mailReceiving", "handle").get();
	}

	private ImapMailInboundChannelAdapterSpec getInboundChannelAdapterConfig() {

		ImapMailReceiver receiver = new ImapMailReceiver();
		receiver.setJavaMailProperties(getProperties());
		
		receiver.setJavaMailAuthenticator(new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
			
				return new PasswordAuthentication("testesspringjava@gmail.com", "testes123456789");
			}
		});
		
		receiver.setShouldDeleteMessages(false);
		receiver.setShouldMarkMessagesAsRead(false);
		receiver.setSearchTermStrategy(new SearchStrategy());

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
	/*
	 * private String getStoreURI() { return
	 * "imaps://testesspringjava%40gmail.com:testes123456789@imap.gmail.com/INBOX";
	 * }
	 */

	private Properties getProperties() {

		Properties prop = new Properties();

		prop.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.imap.socketFactory.fallback", "false");
		prop.setProperty("mail.store.protocol", "imaps");
		prop.setProperty("mail.debug", "true");

		return prop;
	}

}
