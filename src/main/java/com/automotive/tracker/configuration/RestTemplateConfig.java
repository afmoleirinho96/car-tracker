package com.automotive.tracker.configuration;

import com.automotive.tracker.model.annotations.VinDecoderApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean
    @VinDecoderApi
    public RestTemplate vinDecoderRestTemplate(
        @Value("${automotive.vehicleTracker.services.providers.vinDecoderProvider.secretKey}") String secretKey,
        @Value("${automotive.vehicleTracker.services.providers.vinDecoderProvider.apiKey}") String apiKey) {

        return new RestTemplateBuilder().defaultHeader("apiKey", apiKey)
                                        .defaultHeader("secretKey", secretKey)
                                        .setConnectTimeout(Duration.ofSeconds(3))
                                        .setReadTimeout(Duration.ofSeconds(3))
                                        .build();
    }
}
