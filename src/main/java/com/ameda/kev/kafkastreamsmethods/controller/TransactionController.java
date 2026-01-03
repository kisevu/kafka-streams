package com.ameda.kev.kafkastreamsmethods.controller;

import com.ameda.kev.kafkastreamsmethods.events.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger(TransactionController.class);

    public TransactionController(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
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

}
