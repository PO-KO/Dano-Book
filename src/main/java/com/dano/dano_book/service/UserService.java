package com.dano.dano_book.service;

import com.dano.dano_book.DTO.LoginRequestDTO;
import com.dano.dano_book.DTO.LoginResponseDTO;
import com.dano.dano_book.DTO.RegistrationRequestDTO;
import static com.dano.dano_book.constants.RoleEnum.ADMIN;
import static com.dano.dano_book.constants.RoleEnum.USER;

import com.dano.dano_book.constants.RoleEnum;
import com.dano.dano_book.entity.User;
import com.dano.dano_book.repository.RoleRepo;
import com.dano.dano_book.repository.UserRepo;
import com.dano.dano_book.utilities.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authManager;
    private final HandleUserDetailsService userDetails;
    private final JWTService jwt;



    public void register(RegistrationRequestDTO newUser) {
        var role = roleRepo.findByName(ADMIN.name()).orElseThrow(
                () -> new CustomException(HttpStatus.BAD_REQUEST, "Role User was not provided")
        );
        var user = User.builder()
                .firstName(newUser.firstName())
                .lastName(newUser.lastName())
                .email(newUser.email())
                .password(bcrypt.encode(newUser.password()))
                .accountLocked(false)
                .enabled(true)
                .roles(Set.of(role))
                .build();

        repo.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO user) throws CustomException {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.email(), user.password()));

        if (authentication.isAuthenticated()) {
           User loggedUser = (User) userDetails.loadUserByUsername(user.email());
            String accessToken = null;
           try {
               accessToken = jwt.generateToken(loggedUser);
           } catch (Exception ex) {
               throw new CustomException(HttpStatus.FORBIDDEN,"Error");
           }
            return new LoginResponseDTO(
                    loggedUser.getUserId(),
                    loggedUser.getFirstName(),
                    loggedUser.getLastName(),
                    loggedUser.getEmail(),
                    loggedUser.getRoles(),
                    accessToken
            );
        }
        throw new CustomException(HttpStatus.UNAUTHORIZED, "Email or password are incorrect");
    }

}
