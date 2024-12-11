package com.it.majesticcup.services.implementations;

import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.TeamDTO.CreateTeamDTO;
import com.it.majesticcup.models.dtos.TeamDTO.ResponseTeamDTO;
import com.it.majesticcup.models.dtos.TeamDTO.UpdateTeamDTO;
import com.it.majesticcup.models.mappers.TeamMapper;
import com.it.majesticcup.repository.PlayerRepository;
import com.it.majesticcup.repository.TeamRepository;
import com.it.majesticcup.services.interfaces.ITeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService implements ITeamService {

    public final TeamRepository teamRepository;
    public final PlayerRepository playerRepository;
    public final TeamMapper teamMapper;


    @Override
    public ResponseTeamDTO addTeam(CreateTeamDTO createTeamDTO) {

        List<Player> players = playerRepository.findAllById(createTeamDTO.getPlayersId());

        if (players.size() != createTeamDTO.getPlayersId().size()) {
            throw new IllegalArgumentException("Some player IDs are invalid.");
        }

        Team team = teamMapper.toEntity(createTeamDTO);
        Team savedTeam = teamRepository.save(team);
        ResponseTeamDTO responseTeamDTO = teamMapper.toResponseDto(savedTeam);
        responseTeamDTO.setPlayers(players);

        return responseTeamDTO;
    }

    @Override
    public List<ResponseTeamDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();

        return teams.stream().map(team -> {
            ResponseTeamDTO dto = teamMapper.toResponseDto(team);
            List<Player> players = playerRepository.findAllById(team.getPlayersId());
            dto.setPlayers(players);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ResponseTeamDTO getTeamById(String id) {
        Team team = teamRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Team not found"));

        List<Player> players = playerRepository.findAllById(team.getPlayersId());
        ResponseTeamDTO dto = teamMapper.toResponseDto(team);
        dto.setPlayers(players);
        return dto;

    }

    @Override
    public ResponseTeamDTO updateTeam(String id, UpdateTeamDTO updateTeamDTO) {
        Team team = teamRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Team not found"));
        Team updatedTeam = teamMapper.updateEntityFromDTO(updateTeamDTO, team);
        Team savedTeam = teamRepository.save(updatedTeam);
        List<Player> players = playerRepository.findAllById(savedTeam.getPlayersId());
        ResponseTeamDTO dto = teamMapper.toResponseDto(savedTeam);
        dto.setPlayers(players);
        return dto;
    }

    @Override
    public void deleteById(String id) {
        teamRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Team not found"));
        teamRepository.deleteById(id);
    }

}
