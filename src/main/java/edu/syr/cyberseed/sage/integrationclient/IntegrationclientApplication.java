package edu.syr.cyberseed.sage.integrationclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class IntegrationclientApplication {

    private static final Logger log = LoggerFactory.getLogger(IntegrationclientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IntegrationclientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
