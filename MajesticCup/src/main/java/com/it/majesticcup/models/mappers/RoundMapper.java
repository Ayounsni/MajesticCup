package com.it.majesticcup.models.mappers;


import com.it.majesticcup.models.collections.Round;
import com.it.majesticcup.models.dtos.RoundDTO.CreateRoundDTO;
import com.it.majesticcup.models.dtos.RoundDTO.ResponseRoundDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoundMapper {

    ResponseRoundDTO toResponseDto(Round team);

}
