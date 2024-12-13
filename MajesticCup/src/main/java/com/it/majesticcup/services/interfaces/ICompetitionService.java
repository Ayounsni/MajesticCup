package com.it.majesticcup.services.interfaces;

import com.it.majesticcup.models.dtos.CompetitionDTO.CreateCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.ResponseCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.UpdateCompetitionDTO;

import java.util.List;


public interface ICompetitionService {
    ResponseCompetitionDTO addCompetition(CreateCompetitionDTO createCompetitionDTO);
    ResponseCompetitionDTO getCompetitionById(String id);
    ResponseCompetitionDTO updateCompetition(String id , UpdateCompetitionDTO updateCompetitionDTO);
    List<ResponseCompetitionDTO> getAllCompetitions();
    void deleteCompetition(String id);

}
