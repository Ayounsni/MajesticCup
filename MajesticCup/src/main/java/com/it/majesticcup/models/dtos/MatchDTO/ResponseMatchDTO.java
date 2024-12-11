package com.it.majesticcup.models.dtos.MatchDTO;

import com.it.majesticcup.models.dtos.ResultDTO.ResponseResultDTO;
import com.it.majesticcup.models.subdocuments.Result;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMatchDTO {

    private String id;

    private int round;

    private String team1;

    private String team2;

    private ResponseResultDTO result;

    private String winner;
}
