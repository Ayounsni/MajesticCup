package com.it.majesticcup.services.implementations;

import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.TeamDTO.CreateTeamDTO;
import com.it.majesticcup.models.mappers.TeamMapper;
import com.it.majesticcup.repository.TeamRepository;
import com.it.majesticcup.services.interfaces.ITeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService implements ITeamService {

    public final TeamRepository teamRepository;
    public final TeamMapper teamMapper;

    @Override
    public Team addTeam(CreateTeamDTO createTeamDTO) {

        Team team = teamMapper.toEntity(createTeamDTO);

        return teamRepository.save(team);
    }
}
