package com.dano.dano_book.DTO;

import java.util.Set;

import com.dano.dano_book.entity.Author;

public record RequestBookDTO(
        String title,
        int release_year,
        int pages,
        double price,
        Set<Author> authors) {

}
