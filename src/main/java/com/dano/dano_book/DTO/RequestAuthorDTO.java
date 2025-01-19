package com.dano.dano_book.DTO;

import java.util.Date;
import java.util.Set;

import com.dano.dano_book.entity.Book;

public record RequestAuthorDTO(
        String firstName,
        String lastName,
        Date birthDate,
        Set<Book> books) {

}
