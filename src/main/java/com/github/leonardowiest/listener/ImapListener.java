package com.github.leonardowiest.listener;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mail.ImapIdleChannelAdapter;
import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ImapListener {

	/*
	 * @Bean
	 * 
	 * @Qualifier(value = "imapMailFlow") public IntegrationFlow imapMailFlow() {
	 * 
	 * IntegrationFlow flow =
	 * IntegrationFlows.from(Mail.imapIdleAdapter(receiver())).get();
	 * 
	 * return flow; }
	 */

	@Bean
	public void init() {

		ImapIdleChannelAdapter channelAdapter = new ImapIdleChannelAdapter(receiver());

		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

		taskScheduler.setPoolSize(5);
		taskScheduler.afterPropertiesSet();
		
		DirectChannel outputChannel = new DirectChannel();

		channelAdapter.setShouldReconnectAutomatically(true);

		channelAdapter.setTaskScheduler(taskScheduler);

		channelAdapter.setOutputChannel(outputChannel);

		channelAdapter.start();

	}

	private ImapMailReceiver receiver() {

		String user = "aaaaa@gmail.com";

		String password = "aaaaa";

		ImapMailReceiver receiver = new ImapMailReceiver(
				"imaps://" + user + ":" + password + "@imap.gmail.com:993/inbox");

		receiver.setJavaMailProperties(getProperties());

		receiver.setJavaMailAuthenticator(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(user, password);
			}
		});

		return receiver;
	}

	private Properties getProperties() {

		Properties props = new Properties();

		props.setProperty("mail.imap.host", "imap.gmail.com");
		props.setProperty("mail.imap.port", "993");
		props.setProperty("mail.imap.auth.login.disable", "true");
		props.setProperty("mail.imap.auth", "true");
		props.setProperty("mail.imap.ssl.enable", "true");
		props.setProperty("mail.store.protocol", "imaps");
		props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.imap.socketFactory.fallback", "false");
		props.setProperty("mail.imap.connectionpoolsize", "5");
		props.setProperty("mail.debug", "true");

		return props;
	}

}
