package com.it.majesticcup.services.interfaces;

import com.it.majesticcup.models.dtos.CompetitionDTO.CreateCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.ResponseCompetitionDTO;


public interface ICompetitionService {
    ResponseCompetitionDTO addCompetition(CreateCompetitionDTO createCompetitionDTO);

}
