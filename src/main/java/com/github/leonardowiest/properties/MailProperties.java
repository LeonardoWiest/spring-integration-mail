package com.github.leonardowiest.properties;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Leonardo Wiest
 *
 */
@ConfigurationProperties("mail")
@Validated
@Getter
@Setter
public class MailProperties {

	private String protocol;

	private String username;

	private String password;

	private String debug;

	public Properties getProperties() {

		Properties prop = new Properties();

		switch (getProtocol()) {
		case "IMAP":
			prop.setProperty("mail.imap.socketFactory.class", "javax.net.SocketFactory");
			prop.setProperty("mail.imap.socketFactory.fallback", "false");
			prop.setProperty("mail.store.protocol", "imap");
			break;
		case "IMAPS":
			prop.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			prop.setProperty("mail.imap.socketFactory.fallback", "false");
			prop.setProperty("mail.store.protocol", "imaps");
			break;
		case "POP3":
			prop.setProperty("mail.pop3.socketFactory.class", "javax.net.SocketFactory");
			prop.setProperty("mail.pop3.socketFactory.fallback", "false");
			prop.setProperty("mail.store.protocol", "pop3");
		case "POP3S":
			prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			prop.setProperty("mail.pop3.socketFactory.fallback", "false");
			prop.setProperty("mail.store.protocol", "pop3s");
			break;
		default:

			throw new IllegalArgumentException(
					String.format("Protocolo de correio informado não suportado: %s", protocol));
		}

		prop.setProperty("mail.debug", getDebug());

		return prop;
	}
}
