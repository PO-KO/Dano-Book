package com.dano.dano_book.DTO;

import com.dano.dano_book.entity.Role;

import java.util.Set;

public record LoginResponseDTO(
        Integer id,
        String firstName,
        String lastName,
        String email,
        Set<Role> roles,
        String token
) {
}
