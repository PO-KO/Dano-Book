package com.dano.dano_book.service;

import com.dano.dano_book.DTO.ResponseAuthorDTO;
import com.dano.dano_book.DTO.ResponseAuthorWithoutBookDTO;
import com.dano.dano_book.DTO.ResponseBookWithoutAuthor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dano.dano_book.DTO.RequestAuthorDTO;
import com.dano.dano_book.entity.Author;
import com.dano.dano_book.repository.AuthorRepo;
import com.dano.dano_book.repository.BookRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo repo;
    @Autowired
    private BookRepo bookRepo;

    @Transactional
    public void addAuthor(RequestAuthorDTO requestAuthorDTO) {
        Author author = new Author();
        author.setFirstName(requestAuthorDTO.firstName());
        author.setLastName(requestAuthorDTO.lastName());
        author.setBirthDate(requestAuthorDTO.birthDate());
        requestAuthorDTO.books().forEach(book -> book.getAuthors().add(author));
        author.setBooks(requestAuthorDTO.books());

        repo.save(author);
    }

    public List<ResponseAuthorDTO> getAuthors() {
        List<Author> authors = repo.findAll();
        List<ResponseAuthorDTO> responseAuthorListDTO = new ArrayList<>();

        authors.forEach(author -> {
            Set<ResponseBookWithoutAuthor> books = new HashSet<>();

            author.getBooks().forEach((book) -> books.add(new ResponseBookWithoutAuthor(
                    book.getBookId(),
                    book.getTitle(),
                    book.getRelease_year(),
                    book.getPages(),
                    book.getPrice()
                    )));

            responseAuthorListDTO.add(
                    new ResponseAuthorDTO(
                            author.getAuthorId(),
                            author.getFirstName(),
                            author.getLastName(),
                            author.getBirthDate(),
                            books
                    ));
        });

        return responseAuthorListDTO;
    }

    @Transactional
    public void deleteAuthor(Long id) {

        if(repo.existsById(id)) {
            repo.deleteById(id);
        } else throw new IllegalArgumentException("This Author does not exist");

    }

    @Transactional
    public void updateAuthor(Long id, RequestAuthorDTO authorDTO) {
        if(id > 0 && repo.existsById(id)) {
            Author author = repo.findById(id).get();
            if(authorDTO.firstName() != null) author.setFirstName(authorDTO.firstName());
            if(authorDTO.lastName() != null) author.setLastName(authorDTO.lastName());
            if(authorDTO.birthDate() != null) author.setBirthDate(authorDTO.birthDate());
            if(!authorDTO.books().isEmpty()) {
                if(!author.getBooks().isEmpty()) author.removeBooks();
                authorDTO.books().forEach(author::addBook);
            }
            Author save = repo.save(author);
        } else throw new IllegalArgumentException("Id is null or author no exists");
    }

    @Transactional
    public ResponseAuthorDTO getAuthorById(Long id) {
        Author author = repo.findById(id).orElseThrow();
        Set<ResponseBookWithoutAuthor> books = new HashSet<>();

        author.getBooks().forEach(book -> {
            books.add(new ResponseBookWithoutAuthor(
                    book.getBookId(),
                    book.getTitle(),
                    book.getRelease_year(),
                    book.getPages(),
                    book.getPrice()
            ));
        });

        ResponseAuthorDTO responseAuthorDTO = new ResponseAuthorDTO(
                author.getAuthorId(),
                author.getFirstName(),
                author.getLastName(),
                author.getBirthDate(),
                books
        );
        return responseAuthorDTO;

    }
}
