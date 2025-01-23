package com.dano.dano_book.DTO;

import com.dano.dano_book.entity.Book;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.util.Date;
import java.util.Set;

public record RequestAuthorUpdateDTO(
        String firstName,
        String lastName,
        Date birthDate,
        Set<Book> books) {
}
