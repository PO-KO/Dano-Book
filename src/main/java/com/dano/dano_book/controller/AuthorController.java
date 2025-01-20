package com.dano.dano_book.controller;

import com.dano.dano_book.DTO.ResponseAuthorDTO;
import com.dano.dano_book.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dano.dano_book.DTO.RequestAuthorDTO;
import com.dano.dano_book.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private AuthorService service;

    public AuthorController() {

   }

    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ResponseAuthorDTO>> getAuthors() {

        List<ResponseAuthorDTO> authors = service.getAuthors();

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PostMapping("/add")
    public String addAuthor(@RequestBody RequestAuthorDTO requestAuthorDTO) {
        service.addAuthor(requestAuthorDTO);
        return "Created";
    }

}
