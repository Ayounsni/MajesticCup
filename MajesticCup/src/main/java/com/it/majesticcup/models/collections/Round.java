package com.it.majesticcup.models.collections;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rounds")
public class Round {
    @Id
    private String id;

    @NotNull
    private int roundNumber;

    @NotNull
    private String competitionId;
    private List<Match> matches;
}
