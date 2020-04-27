package com.github.leonardowiest.properties;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("mail")
@Validated
@Getter
@Setter
public class MailProperties {

	private String protocol;

	private String username;

	private String password;

	private Properties javaMailProperties = new Properties();
}
