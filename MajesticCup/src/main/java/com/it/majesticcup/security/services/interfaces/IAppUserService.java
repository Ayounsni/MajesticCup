package com.it.majesticcup.security.services.interfaces;

import com.it.majesticcup.security.dtos.AppUserDTO.CreateAppUserDTO;
import com.it.majesticcup.security.dtos.AppUserDTO.ResponseAppUserDTO;
import com.it.majesticcup.security.dtos.AppUserDTO.UpdateAppUserDTO;
import com.it.majesticcup.security.dtos.AuthDTO.RequestLoginDTO;
import com.it.majesticcup.security.dtos.AuthDTO.ResponseLoginDTO;
import com.it.majesticcup.security.dtos.PasswordDTO.ChangePasswordDTO;

import java.util.List;

public interface IAppUserService {
    ResponseAppUserDTO create(CreateAppUserDTO user);
    ResponseAppUserDTO getByUsername(String username);
    List<ResponseAppUserDTO> getAllUsers();
    void deleteUser(String username);
    ResponseAppUserDTO updateUser(String username , UpdateAppUserDTO updateAppUserDTO);
    void changePassword(ChangePasswordDTO changePasswordDTO);
    ResponseLoginDTO login(RequestLoginDTO loginRequest);

}
