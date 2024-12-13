package com.it.majesticcup.services.implementations;

import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.collections.Match;
import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.MatchDTO.CreateMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.ResponseMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.UpdateMatchDTO;
import com.it.majesticcup.models.dtos.ResultDTO.ResponseResultDTO;
import com.it.majesticcup.models.dtos.StatisticDTO.ResponseStatisticDTO;
import com.it.majesticcup.models.dtos.StatisticDTO.TopScorerDTO;
import com.it.majesticcup.models.mappers.MatchMapper;
import com.it.majesticcup.models.subdocuments.Statistic;
import com.it.majesticcup.repository.PlayerRepository;
import com.it.majesticcup.repository.MatchRepository;
import com.it.majesticcup.repository.TeamRepository;
import com.it.majesticcup.services.interfaces.IMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

        Team win = validateWinner(updateMatchDTO.getWinner(), match);

        validatePlayers(updateMatchDTO.getResult().getStatistics());

        Match updatedMatch = matchMapper.updateEntityFromDTO(updateMatchDTO, match);
        Match savedMatch = matchRepository.save(updatedMatch);

        return buildResponseMatchDTO(savedMatch, win);
    }

    @Override
    public ResponseMatchDTO getMatchById(String id) {
        Match match = matchRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Match not found"));
        Team team1 = teamRepository.findById(match.getTeam1()).orElseThrow(
                () -> new IllegalArgumentException("Team 1 not found"));
        Team team2 = teamRepository.findById(match.getTeam2()).orElseThrow(
                () -> new IllegalArgumentException("Team 2 not found"));
        if(match.getWinner() != null){
            Team win = teamRepository.findById(match.getWinner()).orElseThrow(
                    () -> new IllegalArgumentException("Winner team not found"));
            return buildResponseMatchDTO(match, win);
        }

        ResponseMatchDTO matchResponse = new ResponseMatchDTO();
        matchResponse.setId(match.getId());
        matchResponse.setRound(match.getRound());
        matchResponse.setTeam1(team1.getName());
        matchResponse.setTeam2(team2.getName());
        return matchResponse;
    }


    private List<ResponseMatchDTO> getAllMatches() {
        List<Match> matches = matchRepository.findAll();

        List<ResponseMatchDTO> matchResponses = new ArrayList<>();
        for (Match match : matches) {
            Team team1 = teamRepository.findById(match.getTeam1()).orElseThrow(
                    () -> new IllegalArgumentException("Team 1 not found"));
            Team team2 = teamRepository.findById(match.getTeam2()).orElseThrow(
                    () -> new IllegalArgumentException("Team 2 not found"));

            if (match.getWinner() != null) {
                Team win = teamRepository.findById(match.getWinner()).orElseThrow(
                        () -> new IllegalArgumentException("Winner team not found"));
                matchResponses.add(buildResponseMatchDTO(match, win));
            } else {
                ResponseMatchDTO matchResponse = new ResponseMatchDTO();
                matchResponse.setId(match.getId());
                matchResponse.setRound(match.getRound());
                matchResponse.setTeam1(team1.getName());
                matchResponse.setTeam2(team2.getName());
                matchResponses.add(matchResponse);
            }
        }
        return matchResponses;
    }

    @Override
    public List<ResponseMatchDTO> getPlayedMatches() {
            return getAllMatches().stream()
                    .filter(matchRes -> matchRes.getResult() != null
                            && matchRes.getWinner() != null)
                    .collect(Collectors.toList());
    }

    @Override
    public List<ResponseMatchDTO> getNotPlayedMatches() {
        return getAllMatches().stream()
                .filter(matchRes -> matchRes.getResult() == null
                        && matchRes.getWinner() == null)
                .collect(Collectors.toList());
    }

    private Team validateWinner(String winnerId, Match match) {
        Team win = teamRepository.findById(winnerId).orElseThrow(
                () -> new IllegalArgumentException("Team not found"));
        if (!winnerId.equals(match.getTeam1()) && !winnerId.equals(match.getTeam2())) {
            throw new IllegalArgumentException("La team gagnante n'appartient pas au match");
        }
        return win;
    }

    private void validatePlayers(List<Statistic> statistics) {
        for (Statistic statistic : statistics) {
            playerRepository.findById(statistic.getPlayerId()).orElseThrow(
                    () -> new IllegalArgumentException("Player not found"));
        }
    }

    private ResponseMatchDTO buildResponseMatchDTO(Match match, Team win) {
        Team team1 = teamRepository.findById(match.getTeam1()).orElseThrow(
                () -> new IllegalArgumentException("Team 1 not found"));
        Team team2 = teamRepository.findById(match.getTeam2()).orElseThrow(
                () -> new IllegalArgumentException("Team 2 not found"));

        ResponseMatchDTO matchResponse = new ResponseMatchDTO();
        matchResponse.setId(match.getId());
        matchResponse.setRound(match.getRound());
        matchResponse.setTeam1(team1.getName());
        matchResponse.setTeam2(team2.getName());
        matchResponse.setWinner(win.getName());

        ResponseResultDTO result = new ResponseResultDTO();
        result.setTeam2Goals(match.getResult().getTeam2Goals());
        result.setTeam1Goals(match.getResult().getTeam1Goals());
        result.setStatistics(buildResponseStatistics(match.getResult().getStatistics()));

        matchResponse.setResult(result);
        return matchResponse;
    }

    private List<ResponseStatisticDTO> buildResponseStatistics(List<Statistic> statistics) {
        List<ResponseStatisticDTO> statisticDTOS = new ArrayList<>();
        for (Statistic statistic : statistics) {
            ResponseStatisticDTO statisticDTO = new ResponseStatisticDTO();
            Player player = playerRepository.findById(statistic.getPlayerId()).orElseThrow(
                    () -> new IllegalArgumentException("Player not found"));
            statisticDTO.setPlayer(player);
            statisticDTO.setGoals(statistic.getGoals());
            statisticDTO.setAssists(statistic.getAssists());
            statisticDTO.setRedCards(statistic.getRedCards());
            statisticDTO.setYellowCards(statistic.getYellowCards());
            statisticDTOS.add(statisticDTO);
        }
        return statisticDTOS;
    }

    @Override
    public void deleteMatch(String matchId) {
             matchRepository.findById(matchId).orElseThrow(
                () -> new IllegalArgumentException("Match not found"));
        matchRepository.deleteById(matchId);
    }

    @Override
    public List<TopScorerDTO> getTopScorers() {
        List<Statistic> statistics = matchRepository.findAll().stream()
                .filter(match -> match.getResult() != null && match.getResult().getStatistics() != null)
                .flatMap(match -> match.getResult().getStatistics().stream())
                .toList();

        Map<String, Integer> goalsByPlayer = statistics.stream()
                .collect(Collectors.groupingBy(
                        Statistic::getPlayerId,
                        Collectors.summingInt(Statistic::getGoals)
                ));

        return goalsByPlayer.entrySet().stream()
                .map(entry -> {
                    String playerId = entry.getKey();
                    int totalGoals = entry.getValue();

                    String playerName = playerRepository.findById(playerId)
                            .map(Player::getName)
                            .orElse("Unknown Player");

                    return new TopScorerDTO(playerName, totalGoals);
                })
                .sorted((a, b) -> Integer.compare(b.getTotalGoals(), a.getTotalGoals())) // Trier par totalGoals décroissant
                .collect(Collectors.toList());
    }



}
