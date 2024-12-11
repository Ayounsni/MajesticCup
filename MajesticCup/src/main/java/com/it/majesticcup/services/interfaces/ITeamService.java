package com.it.majesticcup.services.interfaces;

import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.TeamDTO.CreateTeamDTO;
import com.it.majesticcup.models.dtos.TeamDTO.ResponseTeamDTO;
import com.it.majesticcup.models.dtos.TeamDTO.UpdateTeamDTO;

import java.util.List;

public interface ITeamService {
    ResponseTeamDTO addTeam(CreateTeamDTO createTeamDTO);
    List<ResponseTeamDTO> getAllTeams();
    ResponseTeamDTO getTeamById(String id);
    ResponseTeamDTO updateTeam(String id, UpdateTeamDTO updateTeamDTO);
    void deleteById(String id);
}
