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
public class MovieItem {
    private Boolean adult;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    private Integer id;
    private String title;
    @JsonProperty("original_title")
    private String originalTitle;
    private String overview;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("media_type")
    private String mediaType;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("genre_ids")
    private List<Integer> genreIds;
    private Double popularity;
    @JsonProperty("release_date")
    private String releaseDate;
    private Boolean video;
    @JsonProperty("vote_average")
    private Double voteAverage;
    @JsonProperty("vote_count")
    private Integer voteCount;
}
