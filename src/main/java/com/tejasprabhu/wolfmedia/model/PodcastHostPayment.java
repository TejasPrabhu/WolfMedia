package com.tejasprabhu.wolfmedia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "paymentID")
public class PodcastHostPayment {
    private Integer paymentID;
    @NotNull(message = "Host ID is required.")
    private Integer hostID;
    @NotNull(message = "Podcast ID is required.")
    private Integer podcastID;
    @NotNull(message = "Episode ID is required.")
    private Integer episodeID;
    @NotNull(message = "Payment amount is required.")
    private BigDecimal paymentAmount;
    @NotNull(message = "Payment date is required.")
    private Date paymentDate;
}