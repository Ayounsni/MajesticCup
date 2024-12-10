package com.it.majesticcup.models.mappers;



import com.it.majesticcup.models.collections.Team;
import com.it.majesticcup.models.dtos.TeamDTO.CreateTeamDTO;
import com.it.majesticcup.models.dtos.TeamDTO.ResponseTeamDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeamMapper {

    Team toEntity(CreateTeamDTO createDTO);
    ResponseTeamDTO toResponseDto(Team team);

}
