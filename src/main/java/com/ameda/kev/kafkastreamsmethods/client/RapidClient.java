package com.ameda.kev.kafkastreamsmethods.client;

import com.ameda.kev.kafkastreamsmethods.events.TmdbTrendingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * Author: kev.Ameda
 */
@HttpExchange("https://ai-movie-recommender.p.rapidapi.com")
public interface RapidClient {

    @GetExchange("/api/trending")
    TmdbTrendingResponse getTrendingMovies(
            @RequestHeader("X-Rapidapi-Key") @Value("${rapidapi.key}") String rapidApiKey,
            @RequestHeader("X-Rapidapi-Host") @Value("${rapidapi.host}") String rapidApiHost
    );

}
