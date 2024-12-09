package com.it.majesticcup.models.subdocuments;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
