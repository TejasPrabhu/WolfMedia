package com.tejasprabhu.wolfmedia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userID")
public class User {

    private Integer userID;

    @NotBlank(message = "Username is required.")
    @Size(max = 255, message = "Username must be at most 255 characters.")
    private String userName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email format is not valid.")
    @Size(max = 255, message = "Email must be at most 255 characters.")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;

    @NotBlank(message = "Subscription type is required.")
    private String subscriptionType;

    @NotBlank(message = "Subscription status is required.")
    private String subscriptionStatus;

    @Size(max = 255, message = "City must be at most 255 characters.")
    private String city;
}
