package com.dano.dano_book.DTO;

import java.util.Date;

public record ResponseAuthorWithoutBookDTO(
        Long authorId,
        String firstName,
        String lastName,
        Date birthDay) {

}
