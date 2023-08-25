package com.tejasprabhu.wolfmedia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "paymentID")
public class ArtistPayment {
    private Integer paymentID;
    @NotNull(message = "Artist ID is required.")
    private Integer artistID;
    @NotNull(message = "Song ID is required.")
    private Integer songID;
    @NotNull(message = "Payment amount is required.")
    private BigDecimal paymentAmount;
    @NotNull(message = "Payment date is required.")
    private Date paymentDate;
}