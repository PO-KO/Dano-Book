package com.dano.dano_book.controller;

import com.dano.dano_book.DTO.LoginRequestDTO;
import com.dano.dano_book.DTO.LoginResponseDTO;
import com.dano.dano_book.DTO.RegistrationRequestDTO;
import com.dano.dano_book.entity.User;
import com.dano.dano_book.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequestDTO newUser) {
        service.register(newUser);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO user) {
        LoginResponseDTO loggedUser = service.login(user);
        return new ResponseEntity<>(loggedUser, HttpStatus.ACCEPTED);
    }

}
