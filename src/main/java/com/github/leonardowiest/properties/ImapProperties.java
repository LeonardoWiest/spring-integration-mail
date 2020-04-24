package com.github.leonardowiest.properties;

import java.util.Properties;

/**
 * 
 * @author Leonardo Wiest
 *
 */
public class ImapProperties {

	public static Properties getImapProperties() {

		// TODO https://docs.spring.io/spring-integration/reference/html/mail.html

		Properties prop = new Properties();
		prop.setProperty("mail.imap.peek", "true");
		prop.setProperty("mail.debug", "true");
		prop.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.imap.socketFactory.fallback", "false");
		prop.setProperty("mail.store.protocol", "imaps");

		return prop;
	}
}
