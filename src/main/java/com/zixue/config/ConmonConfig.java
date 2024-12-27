package com.zixue.config;

import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConmonConfig {

    @Bean
    public SAXReader wuyue(){
        return new SAXReader();
    }
}
