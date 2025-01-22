package com.dano.dano_book.DTO;

import java.util.Date;
import java.util.Set;

import com.dano.dano_book.entity.Book;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

public record RequestAuthorDTO(
        @NotBlank
        @Max(20)
        String firstName,
        @NotBlank
        @Max(20)
        String lastName,
        @NotBlank
        @Past
        Date birthDate,
        Set<Book> books) {

}
