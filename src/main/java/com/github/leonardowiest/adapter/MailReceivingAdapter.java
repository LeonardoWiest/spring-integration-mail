package com.github.leonardowiest.adapter;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Bean
	public IntegrationFlow mailReadFlow() {

		return integrationFlowService.getFlowBuilder().handle("mailReceiving", "handle").get();
	}

}
