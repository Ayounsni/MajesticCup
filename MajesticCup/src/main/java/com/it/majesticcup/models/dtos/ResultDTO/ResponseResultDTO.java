package com.it.majesticcup.models.dtos.ResultDTO;

import com.it.majesticcup.models.dtos.StatisticDTO.ResponseStatisticDTO;
import com.it.majesticcup.models.subdocuments.Statistic;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResultDTO {
    @NotNull
    private int team1Goals;
    @NotNull
    private int team2Goals;

    private List<ResponseStatisticDTO> statistics;
}
