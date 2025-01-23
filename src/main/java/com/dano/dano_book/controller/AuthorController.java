package com.dano.dano_book.controller;

import com.dano.dano_book.DTO.RequestAuthorUpdateDTO;
import com.dano.dano_book.DTO.ResponseAuthorDTO;
import com.dano.dano_book.entity.Author;
import com.dano.dano_book.utilities.CheckID;
import jakarta.validation.Valid;
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
    private final CheckID checkID = new CheckID();

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
    public ResponseEntity<?> addAuthor(@RequestBody @Valid RequestAuthorDTO requestAuthorDTO) {
        service.addAuthor(requestAuthorDTO);
        return new ResponseEntity<>("Author created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        checkID.checkId(id);
        ResponseAuthorDTO author = service.getAuthorById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @Valid RequestAuthorUpdateDTO author) {
        checkID.checkId(id);
        service.updateAuthor(id, author);

        return new ResponseEntity<>("Author: " + id + " Updated successfully", HttpStatus.OK);

    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        checkID.checkId(id);
        service.deleteAuthor(id);

        return new ResponseEntity<>("Author: " + id + " deleted successfully", HttpStatus.OK);
    }

}
