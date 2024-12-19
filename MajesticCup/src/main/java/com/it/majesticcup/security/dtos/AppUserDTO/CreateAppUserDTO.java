package com.it.majesticcup.security.dtos.AppUserDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppUserDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String role = "ROLE_ADMIN";
}
