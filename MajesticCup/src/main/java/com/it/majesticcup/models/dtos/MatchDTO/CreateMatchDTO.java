package com.it.majesticcup.models.dtos.MatchDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMatchDTO {
    @NotNull
    private int round;

    @NotBlank
    private String team1;

    @NotBlank
    private String team2;
}
