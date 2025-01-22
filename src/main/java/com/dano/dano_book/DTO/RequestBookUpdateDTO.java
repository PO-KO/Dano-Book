package com.dano.dano_book.DTO;

import com.dano.dano_book.entity.Author;
import jakarta.validation.constraints.*;

import java.util.Set;

public record RequestBookUpdateDTO(
        @Max(50)
        String title,
        int release_year,
        int pages,
        double price,
        Set<Author> authors
) {
}
