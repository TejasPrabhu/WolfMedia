// Song.java
package com.tejasprabhu.wolfmedia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "songID")
public class Song {

    private Integer songID;

    @NotBlank(message = "Title is required.")
    @Size(max = 255, message = "Title must be at most 255 characters.")
    private String title;

    @NotNull(message = "Artist ID is required.")
    private Integer artistID;

    private Time duration;

    @NotBlank(message = "Genres are required.")
    private String genres;

    @NotNull(message = "Album ID is required.")
    private Integer albumID;

    @NotNull(message = "Play count is required.")
    @Min(value = 0, message = "Play count cannot be negative.")
    private Integer playCount;

    @NotNull(message = "Release date is required.")
    private Date releaseDate;

    @NotBlank(message = "Release country is required.")
    private String releaseCountry;

    @NotBlank(message = "Language is required.")
    private String language;

    @NotNull(message = "Royalty rate is required.")
    @DecimalMax(value = "9999999999.99", message = "Royalty rate is too large.")
    @DecimalMin(value = "0.00", message = "Royalty rate cannot be negative.")
    private Double royaltyRate;

}
