package com.ameda.kev.kafkastreamsmethods.exceptions;

import com.ameda.kev.kafkastreamsmethods.events.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;

/**
 * Author: kev.Ameda
 */
@ControllerAdvice
public class RapidExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(RapidExceptionHandler.class);

    @ExceptionHandler(HttpClientErrorException.TooManyRequests.class)
    public ResponseEntity<ErrorResponse<Void>> handleTooManyRequests(HttpClientErrorException.TooManyRequests ex) {
        log.warn("RapidAPI quota exceeded: {}", ex.getResponseBodyAsString());
        ErrorResponse<Void> response = ErrorResponse.<Void>builder()
                .code(429)
                .message("Movie API quota exceeded. Daily limit reached. Try again tomorrow.")
                .details("Upgrade plan: https://rapidapi.com/AirFU/api/ai-movie-recommender")
                .now(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);
    }
}
