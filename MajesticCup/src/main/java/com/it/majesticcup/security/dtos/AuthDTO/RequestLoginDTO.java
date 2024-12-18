package com.it.majesticcup.security.dtos.AuthDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoginDTO {

    @NotBlank
    private String Username;

    @NotBlank
    private String Password;
}
