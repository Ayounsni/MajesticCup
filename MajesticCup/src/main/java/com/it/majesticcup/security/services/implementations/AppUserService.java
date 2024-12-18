package com.it.majesticcup.security.services.implementations;


import com.it.majesticcup.security.config.Jwt.JwtUtils;
import com.it.majesticcup.security.dtos.AppUserDTO.CreateAppUserDTO;
import com.it.majesticcup.security.dtos.AppUserDTO.ResponseAppUserDTO;
import com.it.majesticcup.security.dtos.AppUserDTO.UpdateAppUserDTO;
import com.it.majesticcup.security.dtos.AuthDTO.RequestLoginDTO;
import com.it.majesticcup.security.dtos.AuthDTO.ResponseLoginDTO;
import com.it.majesticcup.security.dtos.PasswordDTO.ChangePasswordDTO;
import com.it.majesticcup.security.entities.AppUser;
import com.it.majesticcup.security.mappers.AppUserMapper;
import com.it.majesticcup.security.repositories.AppUserRepository;
import com.it.majesticcup.security.services.interfaces.IAppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService implements IAppUserService {

    public final AppUserRepository appUserRepository;
    public final AppUserMapper appUserMapper;
    public final PasswordEncoder passwordEncoder;
    public final HaveIBeenPwnedService haveIBeenPwnedService;
    public final AuthenticationManager authenticationManager;
    public final JwtUtils jwtUtils;

    @Override
    public ResponseAppUserDTO create(CreateAppUserDTO createAppUserDTO) {
        if (appUserRepository.findByUsername(createAppUserDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Ce nom d'utilisateur existe déjà.");
        }
        if (haveIBeenPwnedService.isPasswordPwned(createAppUserDTO.getPassword())) {
            throw new IllegalArgumentException("Le mot de passe est compromis. Veuillez en choisir un autre.");
        }
        AppUser user = appUserMapper.toEntity(createAppUserDTO);
        user.setPassword(passwordEncoder.encode(createAppUserDTO.getPassword()));
        return appUserMapper.toDTO(appUserRepository.save(user)) ;
    }
    @Override
    public ResponseLoginDTO login(RequestLoginDTO loginRequest) {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword())
            );

            String token = jwtUtils.generateToken(loginRequest.getUsername());
            ResponseLoginDTO response = new ResponseLoginDTO();
            response.setToken(token);
            response.setUsername(authentication.getName());
            response.setRole(authentication.getAuthorities().iterator().next().getAuthority());
            return response;

    }

    @Override
    public List<ResponseAppUserDTO> getAllUsers() {
        List<AppUser> users = appUserRepository.findAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userAuth = authentication.getName();
        List<AppUser> newUsers = users.stream().filter(user -> !user.getUsername().equals(userAuth)).toList();
        return newUsers.stream().map(appUserMapper::toDTO).toList();
    }

    @Override
    public void deleteUser(String username){
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userAuth = authentication.getName();
        if(userAuth.equals(username)) {
            throw new IllegalArgumentException("Vous ne pouvez pas supprimer votre propre compte.");
        }
        appUserRepository.delete(user);

    }

    @Override
    public ResponseAppUserDTO updateUser(String username ,UpdateAppUserDTO updateAppUserDTO) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username));
//        AppRole role = appRoleRepository.findById(updateAppUserDTO.getRoleId())
//                .orElseThrow(() -> new IllegalArgumentException("Ce rôle n'existe pas."));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userAuth = authentication.getName();
        if(userAuth.equals(username)) {
            throw new IllegalArgumentException("Vous ne pouvez pas modifier votre propre rôle.");
        }
        AppUser updatedAppUser = appUserMapper.updateEntityFromDTO(updateAppUserDTO, user);
        updatedAppUser = appUserRepository.save(updatedAppUser);
        return appUserMapper.toDTO(updatedAppUser);
    }


    @Override
    public ResponseAppUserDTO getByUsername(String username) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username));

        return appUserMapper.toDTO(user);
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        if(changePasswordDTO.getOldPassword().equals(changePasswordDTO.getNewPassword())) {
            throw new BadCredentialsException("Le nouveau mot de passe ne peut pas être identique à l'ancien mot de passe.");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userAuth = authentication.getName();
        AppUser user = appUserRepository.findByUsername(userAuth)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Ancien mot de passe incorrect");
        }
         user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
         appUserRepository.save(user);
    }
}