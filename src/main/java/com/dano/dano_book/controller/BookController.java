package com.dano.dano_book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dano.dano_book.DTO.RequestBookDTO;
import com.dano.dano_book.DTO.ResponseBookDTO;
import com.dano.dano_book.service.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {


    private BookService service;

    public BookController() {
    }

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<ResponseBookDTO> listResponseBookDTO = service.getAllBooks();
        return new ResponseEntity<>(listResponseBookDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public String addBook(@RequestBody RequestBookDTO request) {

        service.addBook(request);

        return "Created";
    }

    @PatchMapping("/{id}/update")
    public String updateBook(@PathVariable Long id, @RequestBody RequestBookDTO request) {
        service.updateBook(id, request);
        return "Updated!";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteAuthorsFromBook(@PathVariable Long id) {

        service.deleteAuthorsFromBook(id);

        return "Deleted!";
    }

}
