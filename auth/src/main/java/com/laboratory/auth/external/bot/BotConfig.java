package com.laboratory.auth.external.bot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfig {
    @Bean
    public WebhookService webhookService() {
        return new WebhookService();
    }
}
