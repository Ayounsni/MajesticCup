package com.it.majesticcup.services.interfaces;

import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.TeamDTO.CreateTeamDTO;

public interface ITeamService {
    Team addTeam(CreateTeamDTO createTeamDTO);
}
