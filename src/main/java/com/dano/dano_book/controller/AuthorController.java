package com.dano.dano_book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dano.dano_book.DTO.RequestAuthorDTO;
import com.dano.dano_book.service.AuthorService;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping("/add-author")
    public String addAuthor(@RequestBody RequestAuthorDTO requestAuthorDTO) {
        service.addAuthor(requestAuthorDTO);
        return "Created";

    }

}
