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
@EqualsAndHashCode(of = "podcastID")
public class Podcast {

    private Integer podcastID;

    @NotNull(message = "Host ID is required.")
    private Integer hostID;

    @NotBlank(message = "Title is required.")
    @Size(max = 255, message = "Title must be at most 255 characters.")
    private String title;

    @NotNull(message = "Release Date is required.")
    private Date releaseDate;
}
