package com.it.majesticcup.models.subdocuments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {
    private String playerId;
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;
}
