package com.dano.dano_book.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dano.dano_book.entity.Author;
import com.dano.dano_book.repository.AuthorRepo;
import jakarta.transaction.Transactional;
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

    // update book
    @Transactional
    public void updateBook(Long id, RequestBookDTO requestBookDTO) {

        boolean isExist = bookRepo.existsById(id);

        if (id > 0 && isExist) {
            Book book = bookRepo.findById(id).get();
            if (requestBookDTO.title() != null) book.setTitle(requestBookDTO.title());
            if (requestBookDTO.release_year() > 0) book.setRelease_year(requestBookDTO.release_year());
            if (requestBookDTO.pages() > 0) book.setPages(requestBookDTO.pages());
            if (requestBookDTO.price() > 0) book.setPrice(requestBookDTO.price());
            if (!requestBookDTO.authors().isEmpty()) {
                if(!book.getAuthors().isEmpty()) book.removeCourses();
                requestBookDTO.authors().forEach(author -> book.addCourse(author));
            }
            bookRepo.save(book);
        }
    }

    @Transactional
    public void deleteAuthorFromBook(Long bookId, Long authorId) {

        if(bookRepo.existsById(bookId) && authorRepo.existsById(authorId)) {
            Book book = bookRepo.findById(bookId).get();
            Author author = authorRepo.findById(authorId).get();

            if(!book.getAuthors().isEmpty()) book.removeCourse(author);

        }

    }

    @Transactional
    public void deleteAuthorsFromBook(Long id) {
        if(bookRepo.existsById(id)) {
            Book book = bookRepo.findById(id).get();

            if(!book.getAuthors().isEmpty()) book.removeCourses();

        }

    }
}
