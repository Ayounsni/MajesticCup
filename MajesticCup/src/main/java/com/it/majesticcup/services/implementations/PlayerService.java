package com.it.majesticcup.services.implementations;

import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.dtos.PlayerDTO.CreatePlayerDTO;
import com.it.majesticcup.models.mappers.PlayerMapper;
import com.it.majesticcup.repository.PlayerRepository;
import com.it.majesticcup.services.interfaces.IPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService implements IPlayerService {

    public final PlayerRepository playerRepository;
    public final PlayerMapper playerMapper;

    @Override
    public Player addPlayer(CreatePlayerDTO createPlayerDTO) {

        Player player = playerMapper.toEntity(createPlayerDTO);

        return playerRepository.save(player);
    }
    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}
