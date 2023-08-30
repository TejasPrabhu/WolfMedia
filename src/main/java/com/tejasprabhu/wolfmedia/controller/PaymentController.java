package com.tejasprabhu.wolfmedia.controller;

import com.tejasprabhu.wolfmedia.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payments", description = "Manage Payment Processing")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/royalty/calculate")
    @Operation(summary = "Calculate Royalty Payments",
            description = "Calculate royalty payments for a specific year and month.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully initiated calculation of royalty payments"),
            @ApiResponse(responseCode = "400", description = "Invalid year or month provided")
    })
    public void calculateRoyaltyPayments(@RequestParam
                                         @Parameter(description = "Year for which to calculate royalty", required = true)
                                         int year,
                                         @RequestParam
                                         @Parameter(description = "Month for which to calculate royalty", required = true)
                                         int month) {
        logger.info("Received request to calculate royalty payments for year: {} and month: {}", year, month);
        paymentService.calculateRoyaltyPayments(year, month);
    }

    @PostMapping("/distribute/songs")
    @Operation(summary = "Distribute Song Payments",
            description = "Distribute payments for songs across artists.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully initiated distribution of song payments")
    })
    public void distributeSongPayments() {
        logger.info("Received request to distribute song payments");
        paymentService.distributeSongPayments();
    }
}
