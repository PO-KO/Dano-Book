package com.dano.dano_book.service;

import java.util.*;

import com.dano.dano_book.DTO.*;
import com.dano.dano_book.entity.Author;
import com.dano.dano_book.repository.AuthorRepo;
import com.dano.dano_book.utilities.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dano.dano_book.entity.Book;
import com.dano.dano_book.repository.BookRepo;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;

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
    @Transactional
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
    @Transactional
    public List<ResponseBookDTO> getAllBooks() throws CustomException {
        List<Book> books = bookRepo.findAll();
        if(books.isEmpty()) throw new CustomException(HttpStatus.NOT_FOUND, "No books found");
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

    // update book
    @Transactional
    public void updateBook(Long id, RequestBookUpdateDTO requestBookUpdateDTO) throws CustomException {

        if (id > 0) {
            Book book = bookRepo.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "This book does not exist"));
            if (requestBookUpdateDTO.title() != null) book.setTitle(requestBookUpdateDTO.title());
            if (requestBookUpdateDTO.release_year() > 0) book.setRelease_year(requestBookUpdateDTO.release_year());
            if (requestBookUpdateDTO.pages() > 0) book.setPages(requestBookUpdateDTO.pages());
            if (requestBookUpdateDTO.price() > 0) book.setPrice(requestBookUpdateDTO.price());
            if (!requestBookUpdateDTO.authors().isEmpty()) {

                if(!book.getAuthors().isEmpty()) book.removeAuthors();
                requestBookUpdateDTO.authors().forEach(author -> {
                    if(authorRepo.existsById(author.getAuthorId())) {
                        var existedAuth = authorRepo.findById(author.getAuthorId()).get();
                        book.addAuthor(existedAuth);
                    } else {
                        book.addAuthor(author);
                    }
                });
            }
            bookRepo.save(book);
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "ID Invalid");
        }
    }

    @Transactional
    public ResponseBookDTO getBookById(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "This book does not exist"));
        Set<ResponseAuthorWithoutBookDTO> authors = new HashSet<>();

        book.getAuthors().forEach(author -> {
            authors.add(new ResponseAuthorWithoutBookDTO(
                    author.getAuthorId(),
                    author.getFirstName(),
                    author.getLastName(),
                    author.getBirthDate()
            ));
        });

        return new ResponseBookDTO(
                book.getBookId(),
                book.getTitle(),
                book.getRelease_year(),
                book.getPages(),
                book.getPrice(),
                authors

        );

    }

    @Transactional
    public void deleteBookById(Long id) {

        if (id > 0 && bookRepo.existsById(id)) {

            bookRepo.deleteById(id);

        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Id is null or book does not exist");
        }

    }

    @Transactional
    public void deleteAuthorFromBook(Long bookId, Long authorId) {

        if(bookRepo.existsById(bookId) && authorRepo.existsById(authorId)) {
            Book book = bookRepo.findById(bookId).get();
            Author author = authorRepo.findById(authorId).get();

            if(!book.getAuthors().isEmpty()) book.removeAuthor(author);

        }

    }

    @Transactional
    public void deleteAuthorsFromBook(Long id) {
        if(bookRepo.existsById(id)) {
            Book book = bookRepo.findById(id).get();

            if(!book.getAuthors().isEmpty()) book.removeAuthors();

        }

    }

    @Transactional
    public List<ResponseBookDTO> searchBooks(String keyword) {
        List<Book> books = bookRepo.searchBooks(keyword);
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
