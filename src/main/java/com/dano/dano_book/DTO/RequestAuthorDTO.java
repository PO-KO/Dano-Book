package com.dano.dano_book.DTO;

import java.util.Date;
import java.util.Set;

import com.dano.dano_book.entity.Book;
import jakarta.validation.constraints.*;

public record RequestAuthorDTO(
        @NotBlank
        @Size(max = 20)
        String firstName,
        @NotBlank
        @Size(max = 20)
        String lastName,
        @Past
        @NotNull
        Date birthDate,
        Set<Book> books) {

}
