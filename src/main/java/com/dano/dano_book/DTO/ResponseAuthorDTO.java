package com.dano.dano_book.DTO;

import com.dano.dano_book.entity.Book;

import java.util.Date;
import java.util.Set;

public record ResponseAuthorDTO(
        Long id,
        String firstName,
        String lastName,
        Date birthDate,
        Set<ResponseBookWithoutAuthor> books
) {
}
