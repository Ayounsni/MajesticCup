package com.it.majesticcup.models.dtos.StatisticDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopScorerDTO {
    private String playerName;
    private int totalGoals;
}
