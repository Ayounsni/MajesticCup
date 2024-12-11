package com.it.majesticcup.services.implementations;

import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.collections.Match;
import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.MatchDTO.CreateMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.ResponseMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.UpdateMatchDTO;
import com.it.majesticcup.models.mappers.MatchMapper;
import com.it.majesticcup.repository.PlayerRepository;
import com.it.majesticcup.repository.MatchRepository;
import com.it.majesticcup.repository.TeamRepository;
import com.it.majesticcup.services.interfaces.IMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService implements IMatchService {

    public final MatchRepository matchRepository;
    public final TeamRepository teamRepository;
    public final MatchMapper matchMapper;


    @Override
    public ResponseMatchDTO addMatch(CreateMatchDTO createMatchDTO) {

        Team team1 = teamRepository.findById(createMatchDTO.getTeam1()).orElseThrow(
                () -> new IllegalArgumentException("Team 1 not found"));

        Team team2 = teamRepository.findById(createMatchDTO.getTeam2()).orElseThrow(
                () -> new IllegalArgumentException("Team 2 not found"));

        if(createMatchDTO.getTeam1().equals(createMatchDTO.getTeam2())) {
            throw new IllegalArgumentException("Team 1 and Team 2 are the same");
        }
        Match savedMatch = matchMapper.toEntity(createMatchDTO);

        Match match = matchRepository.save(savedMatch);

        ResponseMatchDTO matchResponse = new ResponseMatchDTO();
        matchResponse.setId(match.getId());
        matchResponse.setRound(match.getRound());
        matchResponse.setTeam1(team1.getName());
        matchResponse.setTeam2(team2.getName());
        return matchResponse;

    }


}
