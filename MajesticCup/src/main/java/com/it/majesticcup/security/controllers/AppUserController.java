package com.it.majesticcup.security.controllers;


import com.it.majesticcup.security.dtos.AppUserDTO.CreateAppUserDTO;
import com.it.majesticcup.security.dtos.AppUserDTO.ResponseAppUserDTO;
import com.it.majesticcup.security.dtos.AppUserDTO.UpdateAppUserDTO;
import com.it.majesticcup.security.dtos.AuthDTO.RequestLoginDTO;
import com.it.majesticcup.security.dtos.AuthDTO.ResponseLoginDTO;
import com.it.majesticcup.security.dtos.PasswordDTO.ChangePasswordDTO;
import com.it.majesticcup.security.services.interfaces.IAppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequestMapping("/api")
public class AppUserController {

    @Autowired
    private IAppUserService appUserService;

    @PostMapping("/public/register")
    public ResponseEntity<ResponseAppUserDTO> createAppUser(@Valid @RequestBody CreateAppUserDTO createAppUserDTO) {
        ResponseAppUserDTO appUser = appUserService.create(createAppUserDTO);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @PostMapping("/public/login")
    public ResponseEntity<ResponseLoginDTO> createAppUser(@Valid @RequestBody RequestLoginDTO requestLoginDTO) {
        ResponseLoginDTO login = appUserService.login(requestLoginDTO);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }



    @GetMapping("/admin/users/{username}")
    public ResponseEntity<ResponseAppUserDTO> getAppUserByUsername( @PathVariable("username") String username) {
        ResponseAppUserDTO user = appUserService.getByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<ResponseAppUserDTO>> getAllUsers() {
        List<ResponseAppUserDTO> users = appUserService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/admin/users/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username) {
        appUserService.deleteUser(username);
        return new ResponseEntity<>("Utilisateur est supprimé avec succès", HttpStatus.OK);
    }

    @PutMapping("/admin/users/{username}")
    public ResponseEntity<ResponseAppUserDTO> updateAppUser( @PathVariable("username") String username, @Valid @RequestBody UpdateAppUserDTO updateAppUserDTO) {

        ResponseAppUserDTO updatedAppUser = appUserService.updateUser(username, updateAppUserDTO);
        return new ResponseEntity<>(updatedAppUser, HttpStatus.OK);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        appUserService.changePassword(changePasswordDTO);
        return new ResponseEntity<>("Mot de passe changé avec succès.", HttpStatus.OK);
    }
}
