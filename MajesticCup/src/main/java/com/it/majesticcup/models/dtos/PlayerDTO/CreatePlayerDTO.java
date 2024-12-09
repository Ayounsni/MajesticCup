package com.it.majesticcup.models.dtos.PlayerDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerDTO {
    @NotBlank
    private String name;
    private String surname;
    @NotBlank
    private String position;
    @NotBlank
    private int number;
}
