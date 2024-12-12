package com.it.majesticcup.services.implementations;

import com.it.majesticcup.models.collections.Competition;
import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.CompetitionDTO.CreateCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.ResponseCompetitionDTO;
import com.it.majesticcup.models.dtos.PlayerDTO.CreatePlayerDTO;
import com.it.majesticcup.models.mappers.CompetitionMapper;
import com.it.majesticcup.models.mappers.PlayerMapper;
import com.it.majesticcup.repository.CompetitionRepository;
import com.it.majesticcup.repository.PlayerRepository;
import com.it.majesticcup.repository.TeamRepository;
import com.it.majesticcup.services.interfaces.ICompetitionService;
import com.it.majesticcup.services.interfaces.IPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService implements ICompetitionService {

    public final CompetitionRepository competitionRepository;
    public final TeamRepository teamRepository;
    public final CompetitionMapper competitionMapper;

    @Override
    public ResponseCompetitionDTO addCompetition(CreateCompetitionDTO createCompetitionDTO) {

        List<String> teams = createCompetitionDTO.getTeams();
        int sizeTeam = teams.size();
        if(createCompetitionDTO.getNumberOfTeams() != sizeTeam) {
            throw new IllegalArgumentException("Number of teams does not match");
        }
        Competition competition = competitionMapper.toEntity(createCompetitionDTO);
        Competition savedCompetition =competitionRepository.save(competition);

        List<Team> objectTeams = teamRepository.findAllById(savedCompetition.getTeams());
        if (objectTeams.size() != createCompetitionDTO.getTeams().size()) {
            throw new IllegalArgumentException("Some Teams IDs are invalid.");
        }
        List<String> nameTeams = objectTeams.stream().map(Team::getName).toList();
        ResponseCompetitionDTO responseCompetitionDTO = new ResponseCompetitionDTO();
        responseCompetitionDTO.setId(savedCompetition.getId());
        responseCompetitionDTO.setName(savedCompetition.getName());
        responseCompetitionDTO.setNumberOfTeams(savedCompetition.getNumberOfTeams());
        responseCompetitionDTO.setTeamsNames(nameTeams);
        return responseCompetitionDTO;
    }


}
