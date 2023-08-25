package com.tejasprabhu.wolfmedia.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "labelID")
public class RecordLabel {

    private Integer labelID;

    @NotBlank(message = "Label name is required.")
    @Size(max = 255, message = "Label name must be at most 255 characters.")
    private String labelName;
}
