package com.it.majesticcup.models.dtos.TeamDTO;

import com.it.majesticcup.models.collections.Player;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTeamDTO {
    private String id;

    private String name;

    private String city;

    private List<Player> players;
}
