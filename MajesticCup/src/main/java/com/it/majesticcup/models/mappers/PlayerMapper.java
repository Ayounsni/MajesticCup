package com.it.majesticcup.models.mappers;


import com.it.majesticcup.models.collections.Player;
import com.it.majesticcup.models.dtos.PlayerDTO.CreatePlayerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PlayerMapper {

    Player toEntity(CreatePlayerDTO createDTO);

}
