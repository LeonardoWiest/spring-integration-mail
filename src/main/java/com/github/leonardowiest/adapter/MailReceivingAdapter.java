package com.github.leonardowiest.adapter;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.stereotype.Component;

import com.github.leonardowiest.service.IntegrationFlowService;

/**
 * 
 * @author Leonardo Wiest
 *
 */
@Component
public class MailReceivingAdapter {

	@Autowired
	private IntegrationFlowService integrationFlowService;

	@Value("${mail.username}")
	private String username;

	@Value("${mail.password}")
	private String password;

	@Bean
	public IntegrationFlow mailReadFlow() {

		return integrationFlowService.getFlowBuilder().handle("mailReceiving", "handle").get();
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
