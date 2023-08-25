package com.tejasprabhu.wolfmedia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "artistID")
public class Artist {

    private Integer artistID;

    @NotBlank(message = "Artist name is required.")
    @Size(max = 255, message = "Artist name must be at most 255 characters.")
    private String artistName;

    @NotBlank(message = "Status is required.")
    private String status;

    @NotBlank(message = "Type is required.")
    private String type;

    @NotBlank(message = "Country is required.")
    private String country;

    @NotBlank(message = "Genre is required.")
    private String genre;

    @NotNull(message = "Monthly listeners count is required.")
    @Min(value = 0, message = "Monthly listeners count cannot be negative.")
    private Integer monthlyListeners;

    @NotNull(message = "Label ID is required.")
    private Integer labelID;
}
