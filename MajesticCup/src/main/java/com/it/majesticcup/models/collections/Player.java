package com.it.majesticcup.models.collections;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "players")
public class Player {
    @Id
    private String id;
    @NotBlank
    private String name;

    private String surname;
    @NotBlank
    private String position;
    @NotBlank
    private int number;
}
