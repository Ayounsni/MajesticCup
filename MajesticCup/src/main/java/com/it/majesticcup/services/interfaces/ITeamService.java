package com.it.majesticcup.services.interfaces;

import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.TeamDTO.CreateTeamDTO;
import com.it.majesticcup.models.dtos.TeamDTO.ResponseTeamDTO;

import java.util.List;

public interface ITeamService {
    ResponseTeamDTO addTeam(CreateTeamDTO createTeamDTO);
    List<ResponseTeamDTO> getAllTeams();
}
