package com.it.majesticcup.services.interfaces;

import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.dtos.MatchDTO.CreateMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.ResponseMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.UpdateMatchDTO;
import com.it.majesticcup.models.dtos.PlayerDTO.CreatePlayerDTO;

import java.util.List;

public interface IMatchService {
    ResponseMatchDTO addMatch(CreateMatchDTO createMatchDTO);
    ResponseMatchDTO updateMatch(String id, UpdateMatchDTO updateMatchDTO);
    ResponseMatchDTO getMatchById(String id);
    List<ResponseMatchDTO> getPlayedMatches();
    List<ResponseMatchDTO> getNotPlayedMatches();
    void deleteMatch(String matchId);

}
