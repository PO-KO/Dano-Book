package com.dano.dano_book.service;

import com.dano.dano_book.repository.UserRepo;
import com.dano.dano_book.utilities.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HandleUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws CustomException {
        return userRepo.findByEmail(email).orElseThrow(() -> {
            return new CustomException(HttpStatus.UNAUTHORIZED,"User not found");
        });
    }
}
