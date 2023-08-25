package com.tejasprabhu.wolfmedia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "playcountID")
public class MonthlyPlayCount {
    private Integer playcountID;
    @NotNull(message = "Song ID is required.")
    private Integer songID;
    @NotNull(message = "Year is required.")
    private Integer year;
    @NotNull(message = "Month is required.")
    private Integer month;
    @NotNull(message = "PlayCount date is required.")
    private Integer playCount;
}