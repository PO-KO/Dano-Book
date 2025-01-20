package com.dano.dano_book.DTO;

public record ResponseBookWithoutAuthor(
        Long id,
        String title,
        int release_year,
        int pages,
        double price
) {
}
