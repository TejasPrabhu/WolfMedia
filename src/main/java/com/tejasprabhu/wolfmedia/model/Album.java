package com.tejasprabhu.wolfmedia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "albumID")
public class Album {

    private Integer albumID;

    @NotBlank(message = "Album name is required.")
    @Size(max = 255, message = "Album name must be at most 255 characters.")
    private String albumName;

    @NotNull(message = "Artist ID is required.")
    private Integer artistID;

    @NotNull(message = "Release year is required.")
    private Date releaseYear;

    @NotBlank(message = "Edition is required.")
    @Size(max = 255, message = "Edition must be at most 255 characters.")
    private String edition;
}
