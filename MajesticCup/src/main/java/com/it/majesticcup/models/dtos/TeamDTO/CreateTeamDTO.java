package com.it.majesticcup.models.dtos.TeamDTO;

import com.it.majesticcup.models.dtos.PlayerDTO.CreatePlayerDTO;
import com.it.majesticcup.models.subdocuments.Player;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeamDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    private List<Player> players;
}
