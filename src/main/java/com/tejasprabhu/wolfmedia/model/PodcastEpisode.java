package com.tejasprabhu.wolfmedia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.sql.Time;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "episodeID")
public class PodcastEpisode {

    private Integer episodeID;

    @NotNull(message = "Podcast ID is required.")
    private Integer podcastID;

    @NotBlank(message = "Title is required.")
    @Size(max = 255, message = "Title must be at most 255 characters.")
    private String title;

    @NotNull(message = "Duration is required.")
    private Time duration;

    @NotNull(message = "Listening count is required.")
    @Min(value = 0, message = "Listening count cannot be negative.")
    private Integer listeningCount;

    @NotNull(message = "Ad count is required.")
    @Min(value = 0, message = "Ad count cannot be negative.")
    private Integer adCount;

    @NotNull(message = "Flat fee is required.")
    private BigDecimal flatFee;
}
