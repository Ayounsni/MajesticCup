package com.it.majesticcup.services.interfaces;

import com.it.majesticcup.models.dtos.RoundDTO.CreateRoundDTO;
import com.it.majesticcup.models.dtos.RoundDTO.ResponseRoundDTO;

import java.util.List;


public interface IRoundService {
    ResponseRoundDTO addRound(CreateRoundDTO createRoundDTO);


}
