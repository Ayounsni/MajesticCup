package com.it.majesticcup.models.dtos.RoundDTO;

import com.it.majesticcup.models.collections.Match;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoundDTO {

    @NotNull
    private int roundNumber;

    @NotNull
    private String competitionId;
    private List<String> matches;
}
