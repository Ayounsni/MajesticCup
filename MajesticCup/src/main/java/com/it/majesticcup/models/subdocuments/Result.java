package com.it.majesticcup.models.subdocuments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int team1Goals;
    private int team2Goals;

    private List<Statistic> statistics;
}
