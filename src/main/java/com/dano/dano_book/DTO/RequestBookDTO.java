package com.dano.dano_book.DTO;

import java.util.Set;

import com.dano.dano_book.entity.Author;
import jakarta.validation.constraints.*;

public record RequestBookDTO(
        @NotBlank
        @Size(max = 50)
        String title,
        @NotBlank
        @Positive
        @DecimalMin("1500")
        int release_year,
        @NotBlank
        @Positive
        int pages,
        @NotBlank
        @PositiveOrZero
        double price,
        Set<Author> authors) {

}
