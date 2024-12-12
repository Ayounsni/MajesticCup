package com.it.majesticcup.models.mappers;


import com.it.majesticcup.models.collections.Competition;
import com.it.majesticcup.models.dtos.CompetitionDTO.CreateCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.ResponseCompetitionDTO;
import com.it.majesticcup.models.dtos.CompetitionDTO.UpdateCompetitionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompetitionMapper {

    Competition toEntity(CreateCompetitionDTO createDTO);
    ResponseCompetitionDTO toResponseDto(Competition team);
    Competition updateEntityFromDTO(UpdateCompetitionDTO updateDTO, @MappingTarget Competition entity);

}
