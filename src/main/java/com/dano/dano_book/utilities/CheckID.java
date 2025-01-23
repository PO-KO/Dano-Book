package com.dano.dano_book.utilities;

import org.springframework.http.HttpStatus;

public class CheckID {
    public void checkId(Long id) {
        if(id <= 0) throw new CustomException(HttpStatus.BAD_REQUEST, "ID must be greater then 0");
    }
}
