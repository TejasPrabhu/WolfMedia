package com.tejasprabhu.wolfmedia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "hostID")
public class PodcastHost {

    private Integer hostID;

    @NotBlank(message = "First name is required.")
    @Size(max = 255, message = "First name must be at most 255 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(max = 255, message = "Last name must be at most 255 characters.")
    private String lastName;

    @Email(message = "Email should be valid.")
    @NotBlank(message = "Email is required.")
    @Size(max = 255, message = "Email must be at most 255 characters.")
    private String email;

    @Size(max = 255, message = "Phone number must be at most 255 characters.")
    private String phone;

    @Size(max = 255, message = "City must be at most 255 characters.")
    private String city;
}
