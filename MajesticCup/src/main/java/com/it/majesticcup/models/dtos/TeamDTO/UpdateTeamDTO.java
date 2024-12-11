package com.it.majesticcup.models.dtos.TeamDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTeamDTO {

    private String name;

    private String city;

    private List<String> playersId;
}
