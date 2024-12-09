package com.it.majesticcup.models.collections;


import jakarta.validation.constraints.NotBlank;
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
@Document(collection = "competitions")
public class Competition {
    @Id
    private String id;
    @NotBlank
    private String name;
    @NotNull
    private int numberOfTeams;

    private List<String> teams;
    private int currentRound;
    private List<String> rounds;
}
