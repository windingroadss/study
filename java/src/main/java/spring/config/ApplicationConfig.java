package spring.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.cache.model.CustomKeyGenerator;

@Configuration
public class ApplicationConfig {

    @Bean // (name = "customKeyGenerator")
    public KeyGenerator customKeyGenerator() {
        return new CustomKeyGenerator();
    }
}
