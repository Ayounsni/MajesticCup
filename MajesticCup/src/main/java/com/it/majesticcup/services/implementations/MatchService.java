package com.it.majesticcup.services.implementations;

import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.collections.Match;
import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.MatchDTO.CreateMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.ResponseMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.UpdateMatchDTO;
import com.it.majesticcup.models.dtos.ResultDTO.ResponseResultDTO;
import com.it.majesticcup.models.dtos.StatisticDTO.ResponseStatisticDTO;
import com.it.majesticcup.models.dtos.TeamDTO.ResponseTeamDTO;
import com.it.majesticcup.models.dtos.TeamDTO.UpdateTeamDTO;
import com.it.majesticcup.models.mappers.MatchMapper;
import com.it.majesticcup.models.subdocuments.Statistic;
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
    public final PlayerRepository playerRepository;
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
    @Override
    public ResponseMatchDTO updateMatch(String id, UpdateMatchDTO updateMatchDTO) {
        Match match = matchRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Match not found"));
        Team win = teamRepository.findById(updateMatchDTO.getWinner()).orElseThrow(
                () -> new IllegalArgumentException("Team not found"));
        if(!updateMatchDTO.getWinner().equals(match.getTeam2()) && !updateMatchDTO.getWinner().equals(match.getTeam1())) {
            throw new IllegalArgumentException("had lfarqa makinash f team lawl ou tani");
        }
        List<Statistic> statistics = updateMatchDTO.getResult().getStatistics();
        for(Statistic statistic : statistics) {
             playerRepository.findById(statistic.getPlayerId()).orElseThrow(
                    () -> new IllegalArgumentException("Player not found"));
        }
        Match updatedMatch = matchMapper.updateEntityFromDTO(updateMatchDTO, match);
        Match savedMatch = matchRepository.save(updatedMatch);
        Team team1 = teamRepository.findById(savedMatch.getTeam1()).orElseThrow(
                () -> new IllegalArgumentException("Team 1 not found"));

        Team team2 = teamRepository.findById(savedMatch.getTeam2()).orElseThrow(
                () -> new IllegalArgumentException("Team 2 not found"));
        ResponseMatchDTO matchResponse = new ResponseMatchDTO();
        matchResponse.setId(savedMatch.getId());
        matchResponse.setRound(savedMatch.getRound());
        matchResponse.setTeam1(team1.getName());
        matchResponse.setTeam2(team2.getName());
        matchResponse.setWinner(win.getName());
        List<ResponseStatisticDTO> statisticDTOS = matchResponse.getResult().getStatistics();
        for (int i = 0; i < statistics.size(); i++) {
            ResponseStatisticDTO statisticDTO = statisticDTOS.get(i);
            Statistic statistic = statistics.get(i);
            Player player = playerRepository.findById(statistic.getPlayerId()).orElseThrow();
            statisticDTO.setPlayer(player);
        }
        ResponseResultDTO result = new ResponseResultDTO();
        result.setTeam2Goals(savedMatch.getResult().getTeam2Goals());
        result.setTeam1Goals(savedMatch.getResult().getTeam1Goals());
        result.setStatistics(statisticDTOS);
        matchResponse.setResult(result);
        return matchResponse ;
    }


}
