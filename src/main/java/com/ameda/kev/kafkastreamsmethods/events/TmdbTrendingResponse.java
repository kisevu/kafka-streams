package com.ameda.kev.kafkastreamsmethods.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author: kev.Ameda
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TmdbTrendingResponse {
    private Integer page;
    private List<MovieItem> results;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("total_results")
    private Integer totalResults;
    @JsonProperty("time_window")
    private String timeWindow;
    private String language;
    @JsonProperty("_metadata")
    private Metadata metadata;
}

