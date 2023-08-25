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
public class LabelPayment {
    private Integer paymentID;
    @NotNull(message = "Label ID is required.")
    private Integer labelID;
    @NotNull(message = "Artist ID is required.")
    private Integer artistID;
    @NotNull(message = "Payment amount is required.")
    private BigDecimal paymentAmount;
    @NotNull(message = "Payment date is required.")
    private Date paymentDate;
}