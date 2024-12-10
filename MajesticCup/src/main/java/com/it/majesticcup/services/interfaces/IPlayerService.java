package com.it.majesticcup.services.interfaces;

import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.PlayerDTO.CreatePlayerDTO;
import com.it.majesticcup.models.dtos.TeamDTO.CreateTeamDTO;

import java.util.List;

public interface IPlayerService {
    Player addPlayer(CreatePlayerDTO createPlayerDTO);
    List<Player> getAllPlayers();
}
