package com.dano.dano_book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dano.dano_book.DTO.RequestAuthorDTO;
import com.dano.dano_book.entity.Author;
import com.dano.dano_book.repository.AuthorRepo;
import com.dano.dano_book.repository.BookRepo;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo repo;
    @Autowired
    private BookRepo bookRepo;

    public void addAuthor(RequestAuthorDTO requestAuthorDTO) {
        Author author = new Author();
        author.setFirstName(requestAuthorDTO.firstName());
        author.setLastName(requestAuthorDTO.lastName());
        author.setBirthDate(requestAuthorDTO.birthDate());
        requestAuthorDTO.books().forEach(book -> {
            if (book.getBookId() != null && bookRepo.existsById(book.getBookId())) {
                bookRepo.findById(book.getBookId());

            }
        });
        author.setBooks(requestAuthorDTO.books());

        repo.save(author);
    }

}
