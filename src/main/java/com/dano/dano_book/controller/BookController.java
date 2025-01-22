package com.dano.dano_book.controller;

import java.util.Date;
import java.util.List;

import com.dano.dano_book.DTO.RequestBookUpdateDTO;
import com.dano.dano_book.utilities.CustomException;
import com.dano.dano_book.utilities.ErrorResponse;
import jakarta.validation.Valid;
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
    public String addBook(@RequestBody @Valid RequestBookDTO request) {

        service.addBook(request);

        return "Created";
    }

    @PatchMapping("/{id}/update")
    public String updateBook(@PathVariable Long id, @RequestBody RequestBookUpdateDTO request) {
        service.updateBook(id, request);
        return "Updated!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBookDTO> getBookById(@PathVariable Long id) {

        ResponseBookDTO response = service.getBookById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteBookById(@PathVariable Long id) {
        service.deleteBookById(id);

        return "Deleted";
    }

    @DeleteMapping("/{id}/delete/authors")
    public String deleteAuthorsFromBook(@PathVariable Long id) {

        service.deleteAuthorsFromBook(id);

        return "Deleted!";
    }

    @DeleteMapping("/{bookId}/delete/authors/{authorId}")
    public String deleteAuthorsFromBook(@PathVariable Long bookId, @PathVariable Long authorId) {

        service.deleteAuthorFromBook(bookId, authorId);

        return "Deleted!";
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestParam String keyword) {
        List<ResponseBookDTO> listResponseBookDTO = service.searchBooks(keyword);
        return new ResponseEntity<>(listResponseBookDTO, HttpStatus.OK);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), ex.getMessage(), ex.getStatus().value());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

}
