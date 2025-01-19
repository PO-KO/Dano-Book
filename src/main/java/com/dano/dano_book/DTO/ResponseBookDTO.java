package com.dano.dano_book.DTO;

import java.util.Set;

public record ResponseBookDTO(
        Long id,
        String title,
        int release_year,
        int pages,
        double price,
        Set<ResponseAuthorWithoutBookDTO> authors) {

}
