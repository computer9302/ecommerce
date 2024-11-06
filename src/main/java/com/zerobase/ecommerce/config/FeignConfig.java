package com.zerobase.ecommerce.config;

import feign.auth.BasicAuthRequestInterceptor;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private static final Dotenv dotenv = Dotenv.load();

    String apiKey = dotenv.get("MAILGUN_API_KEY");

    @Qualifier(value = "mailgun")
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("api", apiKey);
    }
}
