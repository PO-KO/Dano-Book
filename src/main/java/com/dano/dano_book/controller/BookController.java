package com.dano.dano_book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dano.dano_book.DTO.RequestBookDTO;
import com.dano.dano_book.DTO.ResponseBookDTO;
import com.dano.dano_book.service.BookService;

@RestController
public class BookController {

    @Autowired
    private BookService service;

    public BookController() {
    }

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        List<ResponseBookDTO> listResponseBookDTO = service.getAllBooks();
        return new ResponseEntity<>(listResponseBookDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public String addBook(@RequestBody RequestBookDTO request) {

        service.addBook(request);

        return "Created";
    }

}
