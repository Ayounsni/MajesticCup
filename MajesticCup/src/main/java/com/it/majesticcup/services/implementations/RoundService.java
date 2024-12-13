package com.it.majesticcup.services.implementations;

import com.it.majesticcup.models.collections.Competition;
import com.it.majesticcup.models.collections.Match;
import com.it.majesticcup.models.collections.Round;
import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.CompetitionDTO.CreateCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.ResponseCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.UpdateCompetitionDTO;
import com.it.majesticcup.models.dtos.MatchDTO.ResponseMatchDTO;
import com.it.majesticcup.models.dtos.RoundDTO.CreateRoundDTO;
import com.it.majesticcup.models.dtos.RoundDTO.ResponseRoundDTO;
import com.it.majesticcup.models.mappers.CompetitionMapper;
import com.it.majesticcup.repository.CompetitionRepository;
import com.it.majesticcup.repository.MatchRepository;
import com.it.majesticcup.repository.RoundRepository;
import com.it.majesticcup.services.interfaces.IRoundService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoundService implements IRoundService {

    private final RoundRepository roundRepository;
    private final CompetitionRepository competitionRepository;
    private final MatchService matchService;
    private final MatchRepository matchRepository;

    @Override
    public ResponseRoundDTO addRound(CreateRoundDTO createRoundDTO) {
        Competition competition = competitionRepository.findById(createRoundDTO.getCompetitionId()).orElseThrow(
                () -> new IllegalArgumentException("Competition not found"));
        List<Match> matches =  matchRepository.findAllById(createRoundDTO.getMatches());
        if (matches.size() != createRoundDTO.getMatches().size()) {
            throw new IllegalArgumentException("Some matches IDs are invalid.");
        }
        Round round = new Round();
        round.setCompetitionId(competition.getId());
        round.setRoundNumber(createRoundDTO.getRoundNumber());
        round.setMatches(matches);
        Round savedRound = roundRepository.save(round);

        List<String> roundId =competition.getRounds();
        if (roundId == null) {
            roundId = new ArrayList<>();
        }
        roundId.add(savedRound.getId());
        competition.setRounds(roundId);
        Competition newCompetition = competitionRepository.save(competition);

        ResponseRoundDTO responseRoundDTO = new ResponseRoundDTO();
        responseRoundDTO.setId(savedRound.getId());
        responseRoundDTO.setCompetitionName(newCompetition.getName());
        responseRoundDTO.setRoundNumber(round.getRoundNumber());
        for (Match match : matches) {
            List<ResponseMatchDTO> responseMatchDTOList = new ArrayList<>();
            ResponseMatchDTO responseMatchDTO = matchService.getMatchById(match.getId());
            responseMatchDTOList.add(responseMatchDTO);
            responseRoundDTO.setMatches(responseMatchDTOList);
        }
        return responseRoundDTO;
    }

    @Override
    public ResponseRoundDTO getRoundById(String id) {
        Round round = roundRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Round not found"));

        Competition competition = competitionRepository.findById(round.getCompetitionId()).orElseThrow(
                () -> new IllegalArgumentException("Competition not found"));

        List<ResponseMatchDTO> responseMatchDTOList = new ArrayList<>();
        for (Match match : round.getMatches()) {
            ResponseMatchDTO responseMatchDTO = matchService.getMatchById(match.getId());
            responseMatchDTOList.add(responseMatchDTO);
        }

        ResponseRoundDTO responseRoundDTO = new ResponseRoundDTO();
        responseRoundDTO.setId(round.getId());
        responseRoundDTO.setCompetitionName(competition.getName());
        responseRoundDTO.setRoundNumber(round.getRoundNumber());
        responseRoundDTO.setMatches(responseMatchDTOList);

        return responseRoundDTO;
    }

    @Override
    public void deleteRound(String id) {
        Round round = roundRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Round not found"));
        Competition competition = competitionRepository.findById(round.getCompetitionId()).orElseThrow();
        List<String> roundId =competition.getRounds();
        roundId.remove(round.getId());
        competition.setRounds(roundId);
        competitionRepository.save(competition);
        roundRepository.delete(round);
    }


}