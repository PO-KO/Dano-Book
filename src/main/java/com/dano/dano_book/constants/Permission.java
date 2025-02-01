package com.dano.dano_book.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    // Book
    READ_BOOK("books:read"),
    CREATE_BOOK("books:create"),
    UPDATE_BOOK("books:update"),
    DELETE_BOOK("books:delete"),
    //  author
    READ_AUTHOR("authors:read"),
    CREATE_AUTHOR("authors:create"),
    UPDATE_AUTHOR("authors:update"),
    DELETE_AUTHOR("authors:delete");

    private final String permission;

}
