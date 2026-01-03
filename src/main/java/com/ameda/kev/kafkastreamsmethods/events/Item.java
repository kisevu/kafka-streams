package com.ameda.kev.kafkastreamsmethods.events;

/**
 * Author: kev.Ameda
 */
public record Item(
        String itemId,
        String name,
        double price,
        int quantity
) {
}
