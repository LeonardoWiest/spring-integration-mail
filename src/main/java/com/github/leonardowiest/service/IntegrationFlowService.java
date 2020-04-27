package com.github.leonardowiest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.dsl.IntegrationFlowBuilder;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mail.dsl.MailInboundChannelAdapterSpec;
import org.springframework.stereotype.Service;

@Service
public class IntegrationFlowService {

	@Autowired
	private ImapFlowService imapFlowService;

	@Value("${mail.protocol}")
	private String protocol;

	IntegrationFlowBuilder flowBuilder;

	MailInboundChannelAdapterSpec<?, ?> adapterSpec;

	public IntegrationFlowBuilder getFlowBuilder() {

		switch (protocol.toUpperCase()) {
		case "IMAP":
		case "IMAPS":
			adapterSpec = imapFlowService.getImapFlow();
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
}
