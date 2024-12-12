package com.it.majesticcup.models.dtos.CompetitionDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompetitionDTO {

    @NotBlank
    private String name;
    @NotNull
    private int numberOfTeams;

    private List<String> teams;

}
