package com.dano.dano_book.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dano.dano_book.DTO.RequestBookDTO;
import com.dano.dano_book.DTO.ResponseAuthorWithoutBookDTO;
import com.dano.dano_book.DTO.ResponseBookDTO;
import com.dano.dano_book.entity.Book;
import com.dano.dano_book.repository.BookRepo;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public BookService() {
    }

    public BookRepo getBookRepo() {
        return bookRepo;
    }

    public void setBookRepo(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    // Add book
    public void addBook(RequestBookDTO requestBookDTO) {

        Book book = new Book();
        book.setTitle(requestBookDTO.title());
        book.setPages(requestBookDTO.pages());
        book.setPrice(requestBookDTO.price());
        book.setRelease_year(requestBookDTO.release_year());
        requestBookDTO.authors().forEach(author -> author.getBooks().add(book));
        book.setAuthors(requestBookDTO.authors());
        bookRepo.save(book);
    }

    // Get all books
    public List<ResponseBookDTO> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        List<ResponseBookDTO> listResponseBookDTO = new ArrayList<>();
        books.forEach(book -> {
            Set<ResponseAuthorWithoutBookDTO> authors = new HashSet<>();
            book.getAuthors().forEach(author -> authors.add(new ResponseAuthorWithoutBookDTO(author.getAuthorId(), author.getFirstName(), author.getLastName(), author.getBirthDate())));

            listResponseBookDTO.add(new ResponseBookDTO(
                    book.getBookId(),
                    book.getTitle(),
                    book.getRelease_year(),
                    book.getPages(),
                    book.getPrice(),
                    authors));
        });
        return listResponseBookDTO;

    }

}
