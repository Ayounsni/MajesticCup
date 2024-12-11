package com.it.majesticcup.models.dtos.ResultDTO;

import com.it.majesticcup.models.dtos.StatisticDTO.ResponseStatisticDTO;
import com.it.majesticcup.models.subdocuments.Statistic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResultDTO {
    private int team1Goals;
    private int team2Goals;

    private List<ResponseStatisticDTO> statistics;
}
