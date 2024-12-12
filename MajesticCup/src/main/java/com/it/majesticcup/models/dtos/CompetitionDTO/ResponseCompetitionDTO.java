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
public class ResponseCompetitionDTO {

    private String id;
    private String name;
    private int numberOfTeams;
    private List<String> teamsNames;
    private int currentRound;
    private List<Integer> rounds;
}
