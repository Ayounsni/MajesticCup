package com.it.majesticcup.security.dtos.AppUserDTO;

import com.it.majesticcup.security.dtos.AppRoleDTO.EmbeddableAppRoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAppUserDTO {

    private String id;

    private String username;

    private String role;
}
