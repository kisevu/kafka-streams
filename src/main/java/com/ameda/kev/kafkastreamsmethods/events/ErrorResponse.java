package com.ameda.kev.kafkastreamsmethods.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

/**
 * Author: kev.Ameda
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse<T>{
private Integer code;
private String message;
private String details;
private Instant now;

}
