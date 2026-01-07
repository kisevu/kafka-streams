package com.ameda.kev.kafkastreamsmethods.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.service.registry.ImportHttpServices;

/**
 * Author: kev.Ameda
 */
@Configuration
@ImportHttpServices(types = {RapidClient.class,IndeedClient.class})
//@ImportHttpServices(basePackages = "com.ameda.kev.kafkastreamsmethods.client",
//        types = {RapidClient.class}, group = "rapid-clientt")

public class HttpClientConfig {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

