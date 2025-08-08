package com.Ecommerce.demo.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class AppLocaleConfig {
    @Bean
    public Locale getAppLocale() {
        return Locale.forLanguageTag("en-ZA");
    }
}
