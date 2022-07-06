package indi.ljf.spring.config;

import indi.ljf.spring.service.LightService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean(destroyMethod = "shutdown1")
    public LightService getTransmission() {
        return new LightService();
    }
}
