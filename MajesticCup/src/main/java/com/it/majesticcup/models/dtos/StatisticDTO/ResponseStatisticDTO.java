package com.it.majesticcup.models.dtos.StatisticDTO;

import com.it.majesticcup.models.collections.Player;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatisticDTO {
    private Player player;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;
}
