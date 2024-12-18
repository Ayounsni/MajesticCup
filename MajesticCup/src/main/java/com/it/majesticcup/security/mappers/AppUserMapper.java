package com.it.majesticcup.security.mappers;


import com.it.majesticcup.security.dtos.AppUserDTO.CreateAppUserDTO;
import com.it.majesticcup.security.dtos.AppUserDTO.ResponseAppUserDTO;
import com.it.majesticcup.security.dtos.AppUserDTO.UpdateAppUserDTO;
import com.it.majesticcup.security.entities.AppUser;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppUserMapper {

    AppUser toEntity(CreateAppUserDTO createDTO);

    AppUser updateEntityFromDTO(UpdateAppUserDTO updateAppUserDTO, @MappingTarget AppUser entity);

    ResponseAppUserDTO toDTO(AppUser entity);
}
