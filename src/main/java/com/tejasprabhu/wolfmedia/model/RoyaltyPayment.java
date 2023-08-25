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
@EqualsAndHashCode(of = "royaltyPaymentID")
public class RoyaltyPayment {
    private Integer royaltyPaymentID;
    @NotNull(message = "Song ID is required.")
    private Integer songID;
    @NotNull(message = "Payment date is required.")
    private Date paymentDate;
    @NotNull(message = "Calculated amount is required.")
    private BigDecimal calculatedAmount;
    @NotBlank(message = "Payment status is required.")
    private String paymentStatus;
}
