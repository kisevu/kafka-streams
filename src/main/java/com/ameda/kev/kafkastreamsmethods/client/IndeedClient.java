package com.ameda.kev.kafkastreamsmethods.client;

import com.ameda.kev.kafkastreamsmethods.events.IndeedJobDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * Author: kev.Ameda
 */
@HttpExchange("https://indeed12.p.rapidapi.com")
public interface IndeedClient {

    @GetExchange("/job/{jobId}")
    IndeedJobDetails getJobDetails(@PathVariable("jobId") String jobId ,
                                   @RequestParam("locality") String locality,
                                   @RequestHeader("X-Rapidapi-key") @Value("${rapidapi.jobs.key}") String rapidApiKey,
                                   @RequestHeader("X-Rapidapi-Host") @Value("${rapidapi.jobs.host}") String rapidApiHost);
}
