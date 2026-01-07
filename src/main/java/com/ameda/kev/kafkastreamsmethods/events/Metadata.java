package com.ameda.kev.kafkastreamsmethods.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: kev.Ameda
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Metadata {
    @JsonProperty("trending_period")
    private String trendingPeriod;
    @JsonProperty("request_timestamp")
    private String requestTimestamp;
    @JsonProperty("next_page")
    private Integer nextPage;
    @JsonProperty("prev_page")
    private Integer prevPage;
}
