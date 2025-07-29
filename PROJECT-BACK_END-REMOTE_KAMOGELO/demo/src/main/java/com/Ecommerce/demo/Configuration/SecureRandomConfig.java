package com.Ecommerce.demo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;
import java.util.Random;

@Configuration
public class SecureRandomConfig {

    @Bean
    public Random getRandom() {
        return new SecureRandom();
    }
}
