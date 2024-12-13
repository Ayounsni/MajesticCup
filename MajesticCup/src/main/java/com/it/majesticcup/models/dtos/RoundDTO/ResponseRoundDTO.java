package com.it.majesticcup.models.dtos.RoundDTO;

import com.it.majesticcup.models.collections.Competition;
import com.it.majesticcup.models.collections.Match;
import com.it.majesticcup.models.dtos.MatchDTO.ResponseMatchDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRoundDTO {
    private String id ;
    private int roundNumber;
    private String competitionName;
    private List<ResponseMatchDTO> matches;
}
