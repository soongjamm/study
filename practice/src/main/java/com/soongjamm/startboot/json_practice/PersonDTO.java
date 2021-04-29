package com.soongjamm.startboot.json_practice;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PersonDTO {
    @NotEmpty(message = "not allow empty")
    @NotBlank(message = "not allow blank")
    private String firstName;
    @NotNull(message = "not allow null")
    private String lastName;
}
