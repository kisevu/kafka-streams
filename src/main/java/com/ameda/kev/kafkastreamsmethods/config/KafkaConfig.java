package com.ameda.kev.kafkastreamsmethods.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic createTransactionTopic() {
        return new NewTopic("transactions",
                3, (short) 1);
    }

    @Bean
    public NewTopic createFraudAlertTopic() {
        return new NewTopic("fraud-alerts",
                3, (short) 1);
    }

    @Bean
    public NewTopic createFilterNotTopic() {
        return new NewTopic("filter-not-topic",
                3 , (short)  1);
    }

    @Bean
    public NewTopic createDebitTopic() {
        return new NewTopic("debit-topic",
                3 , (short)  1);
    }

    @Bean
    public NewTopic createCreditTopic() {
        return new NewTopic("credit-topic",
                3 , (short)  1);
    }


}