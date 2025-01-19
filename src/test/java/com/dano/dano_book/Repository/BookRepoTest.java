package com.dano.dano_book.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dano.dano_book.entity.Author;
import com.dano.dano_book.entity.Book;
import com.dano.dano_book.repository.BookRepo;

@SpringBootTest
class BookRepoTest {

    @Autowired
    private BookRepo bookRepo;

    @Test
    public void saveBook() {
        Book book = new Book();
        book.setTitle("Game of thrones");
        book.setRelease_year(2006);
        book.setPages(2016);
        book.setPrice(100);

        Author author = new Author();
        author.setFirstName("Ayoub");
        author.setLastName("Daoui");
        author.getBooks().add(book);

        book.getAuthors().add(author);

        bookRepo.save(book);
    }

}
