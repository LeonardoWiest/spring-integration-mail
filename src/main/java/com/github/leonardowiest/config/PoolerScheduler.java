package com.github.leonardowiest.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.scheduling.support.PeriodicTrigger;

@Configuration
public class PoolerScheduler {

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata defaultPoller() {

		PollerMetadata poller = new PollerMetadata();

		// A cada quanto tempo deve requisitar mensagens
		poller.setTrigger(new PeriodicTrigger(1, TimeUnit.MINUTES));

		return poller;
	}
}
