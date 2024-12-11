package com.it.majesticcup.models.mappers;


import com.it.majesticcup.models.collections.Match;
import com.it.majesticcup.models.dtos.MatchDTO.CreateMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.ResponseMatchDTO;
import com.it.majesticcup.models.dtos.MatchDTO.UpdateMatchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MatchMapper {

    Match toEntity(CreateMatchDTO createDTO);
    ResponseMatchDTO toResponseDto(Match team);
    Match updateEntityFromDTO(UpdateMatchDTO updateDTO, @MappingTarget Match entity);

}
