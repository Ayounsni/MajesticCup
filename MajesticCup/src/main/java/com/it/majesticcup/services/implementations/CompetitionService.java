package com.it.majesticcup.services.implementations;

import com.it.majesticcup.models.collections.Competition;
import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.collections.Round;
import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.CompetitionDTO.CreateCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.ResponseCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.UpdateCompetitionDTO;
import com.it.majesticcup.models.dtos.PlayerDTO.CreatePlayerDTO;
import com.it.majesticcup.models.mappers.CompetitionMapper;
import com.it.majesticcup.models.mappers.PlayerMapper;
import com.it.majesticcup.repository.CompetitionRepository;
import com.it.majesticcup.repository.PlayerRepository;
import com.it.majesticcup.repository.RoundRepository;
import com.it.majesticcup.repository.TeamRepository;
import com.it.majesticcup.services.interfaces.ICompetitionService;
import com.it.majesticcup.services.interfaces.IPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionService implements ICompetitionService {

    private final CompetitionRepository competitionRepository;
    private final TeamRepository teamRepository;
    private final CompetitionMapper competitionMapper;
    private final RoundRepository roundRepository;

    @Override
    public ResponseCompetitionDTO addCompetition(CreateCompetitionDTO createCompetitionDTO) {
        if (createCompetitionDTO.getNumberOfTeams() != createCompetitionDTO.getTeams().size()) {
            throw new IllegalArgumentException("Number of teams does not match");
        }

        Competition competition = competitionMapper.toEntity(createCompetitionDTO);
        Competition savedCompetition = competitionRepository.save(competition);

        List<Team> objectTeams = teamRepository.findAllById(savedCompetition.getTeams());
        if (objectTeams.size() != createCompetitionDTO.getTeams().size()) {
            throw new IllegalArgumentException("Some Teams IDs are invalid.");
        }

        return mapToResponseDTO(savedCompetition);
    }

    @Override
    public ResponseCompetitionDTO getCompetitionById(String id) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Competition not found"));
        return mapToResponseDTO(competition);
    }

    @Override
    public ResponseCompetitionDTO updateCompetition(String id, UpdateCompetitionDTO updateCompetitionDTO) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Competition not found"));

        if (competition.getRounds() == null) {
            throw new IllegalArgumentException(
                    "Impossible de modifier le round actuel : aucun round n'a encore été créé pour cette compétition.");
        }

        int newCurrentRound = updateCompetitionDTO.getCurrentRound();
        List<Integer> existingRounds = roundRepository.findAllById(competition.getRounds())
                .stream()
                .map(Round::getRoundNumber)
                .toList();

        if (!existingRounds.contains(newCurrentRound)) {
            throw new IllegalArgumentException(
                    "Le round actuel proposé (" + newCurrentRound + ") n'existe pas dans la liste des rounds de la compétition.");
        }

        competition.setCurrentRound(newCurrentRound);
        competitionRepository.save(competition);
        return mapToResponseDTO(competition);
    }

    @Override
    public List<ResponseCompetitionDTO> getAllCompetitions() {
        return competitionRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private ResponseCompetitionDTO mapToResponseDTO(Competition competition) {
        ResponseCompetitionDTO responseCompetitionDTO = new ResponseCompetitionDTO();
        responseCompetitionDTO.setId(competition.getId());
        responseCompetitionDTO.setName(competition.getName());
        responseCompetitionDTO.setCurrentRound(competition.getCurrentRound());
        responseCompetitionDTO.setNumberOfTeams(competition.getNumberOfTeams());

        List<Team> objectTeams = teamRepository.findAllById(competition.getTeams());
        List<String> nameTeams = objectTeams.stream().map(Team::getName).toList();
        responseCompetitionDTO.setTeamsNames(nameTeams);

        List<Integer> roundNumbers = Optional.ofNullable(competition.getRounds())
                .map(roundIds -> roundRepository.findAllById(roundIds)
                        .stream()
                        .map(Round::getRoundNumber)
                        .toList())
                .orElse(Collections.emptyList());
        responseCompetitionDTO.setRounds(roundNumbers);

        return responseCompetitionDTO;
    }
    @Override
    public void deleteCompetition(String id) {
        Competition competition = competitionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Competition not found"));
        if (competition.getRounds() != null) {
            List<String> rounds = competition.getRounds();
            List<Round> roundList = roundRepository.findAllById(rounds);
            roundRepository.deleteAll(roundList);
        }
        competitionRepository.delete(competition);
    }
}