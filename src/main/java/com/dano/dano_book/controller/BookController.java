package com.dano.dano_book.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dano.dano_book.DTO.RequestBookDTO;
import com.dano.dano_book.DTO.RequestBookUpdateDTO;
import com.dano.dano_book.DTO.ResponseBookDTO;
import com.dano.dano_book.service.BookService;
import com.dano.dano_book.utilities.CheckID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;
    private final CheckID checkID = new CheckID();

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<ResponseBookDTO> listResponseBookDTO = service.getAllBooks();
        return new ResponseEntity<>(listResponseBookDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody @Valid RequestBookDTO request) {

        service.addBook(request);

        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<String> updateBook(
            @PathVariable Long id,
            @RequestBody
            @Valid RequestBookUpdateDTO request) {
        checkID.checkId(id);
        service.updateBook(id, request);
        return new ResponseEntity<>("Book " + id + " Updated successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBookDTO> getBookById(@PathVariable Long id) {

        checkID.checkId(id);

        ResponseBookDTO response = service.getBookById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        checkID.checkId(id);
        service.deleteBookById(id);

        return new ResponseEntity<>("Book " + id + "deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete/authors")
    public ResponseEntity<String> deleteAuthorsFromBook(@PathVariable Long id) {
        checkID.checkId(id);
        service.deleteAuthorsFromBook(id);

        return new ResponseEntity<>("Authors from book " + id + " deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}/delete/authors/{authorId}")
    public ResponseEntity<String> deleteAuthorsFromBook(@PathVariable Long bookId, @PathVariable Long authorId) {
        checkID.checkId(bookId);
        checkID.checkId(authorId);
        service.deleteAuthorFromBook(bookId, authorId);

        return new ResponseEntity<>("Author " + authorId + " from book " + bookId + " deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestParam String keyword) {
        List<ResponseBookDTO> listResponseBookDTO = service.searchBooks(keyword);
        return new ResponseEntity<>(listResponseBookDTO, HttpStatus.OK);
    }

}
