package com.tejasprabhu.wolfmedia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "collaborationID")
public class Collaboration  {

    private Integer collaborationID;

    @NotNull(message = "Song ID is required.")
    private Integer songID;

    @NotNull(message = "Artist ID is required.")
    private Integer artistID;
}
