package com.ameda.kev.kafkastreamsmethods.events;

public record Transaction(
        String transactionId,
        String userId,
        double amount,
        String timestamp
) {
}
