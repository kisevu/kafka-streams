package com.ameda.kev.kafkastreamsmethods.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

/**
 * Author: kev.Ameda
 */
@Configuration
@ImportHttpServices(types = {RapidClient.class,IndeedClient.class})
//@ImportHttpServices(basePackages = "com.ameda.kev.kafkastreamsmethods.client",
//        types = {RapidClient.class}, group = "rapid-client")
public class HttpClientConfig {

    @Value("${rapidapi.jobs.key}")
    private String rapidApiKey;

    @Value("${rapidapi.jobs.host}")
    private String rapidApiHost;

}

