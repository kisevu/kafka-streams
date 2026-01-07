package com.ameda.kev.kafkastreamsmethods.controller;

import com.ameda.kev.kafkastreamsmethods.client.IndeedClient;
import com.ameda.kev.kafkastreamsmethods.client.RapidClient;
import com.ameda.kev.kafkastreamsmethods.events.IndeedJobDetails;
import com.ameda.kev.kafkastreamsmethods.events.TmdbTrendingResponse;
import com.ameda.kev.kafkastreamsmethods.events.RestReq;
import com.ameda.kev.kafkastreamsmethods.events.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger(TransactionController.class);
    private final RapidClient rapidClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${rapidapi.key}")
    private String rapidApiKey;
    @Value("${rapidapi.host}")
    private String rapidApiHost;

    @Value("${rapidapi.jobs.key}")
    private String indeedKey;

    @Value("${rapidapi.jobs.host}")
    private String indeedHost;

    private RestClient restClient;

    private final IndeedClient indeedClient;

    public TransactionController(KafkaTemplate<String, Transaction> kafkaTemplate,
                                 RapidClient rapidClient, IndeedClient indeedClient) {
        this.kafkaTemplate = kafkaTemplate;
        this.rapidClient = rapidClient;
        this.indeedClient = indeedClient;
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publishTransaction(){
        List<Transaction> transactions = readTransactionResource();
        for (Transaction txn: transactions){
            kafkaTemplate.send("transactions",txn.transactionId(),txn);
        }
        return ResponseEntity.ok(" Published- "+ transactions.size() + " transactions to kafka.");
    }

    private List<Transaction> readTransactionResource(){
        try(InputStream is = getClass().getResourceAsStream("/transactions.json")){
            return mapper.readValue(is, new TypeReference<List<Transaction>>(){});
        }catch (IOException ex){
            log.error(" Error occurred : {} with cause : {}",ex.getMessage(), ex.getCause());
            throw new RuntimeException("Failed to parse transactions.json");
        }
    }

    @GetMapping("/jobs")
    public ResponseEntity<?> workable(){
        TmdbTrendingResponse tmdbTrendingResponse = rapidClient.getTrendingMovies(rapidApiKey, rapidApiHost);
        log.info(" response : {}", tmdbTrendingResponse);
        return ResponseEntity.ok(tmdbTrendingResponse);
    }


    @GetMapping("/indeed/{jobId}")
    public ResponseEntity<?> getIndeedJobDetail(@PathVariable("jobId") String jobId,
                                                @RequestParam("locality") String locality){
        IndeedJobDetails jobDetails = indeedClient.getJobDetails(jobId, locality, indeedKey, indeedHost);
        return ResponseEntity.ok(jobDetails);
    }


    @GetMapping
    public void result(){
        this.restClient = RestClient.builder()
                .baseUrl("http://")
                .defaultHeader("X-Rapidapi-key",rapidApiKey)
                .defaultHeader("X-Rapidapi-host",rapidApiHost)
                .build();
    }

    public IndeedJobDetails getJobDetails(String jobId, String locality,RestClient restClient){
        //the passed in restClient is the similar one used
        return restClient.get()
                .uri( uriBuilder -> uriBuilder
                        .path("/job/{jobId}")
                        .queryParam("locality",locality)
                        .build(jobId))
                .retrieve()
                .body(IndeedJobDetails.class);
    }



}
