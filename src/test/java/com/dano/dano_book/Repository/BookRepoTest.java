package com.dano.dano_book.Repository;

import com.github.javafaker.Faker;
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
        Faker faker = new Faker();
        for(int i = 0; i < 100; i++) {
            Book book = new Book();
            Author author = new Author();
            book.setTitle(faker.book().title());
            book.setRelease_year(faker.number().numberBetween(1500, 2025));
            book.setPages(faker.number().numberBetween(80, 5000));
            book.setPrice(faker.number().randomDouble(2, 5, 1000));
            author.setFirstName(faker.name().firstName());
            author.setLastName(faker.name().lastName());
            author.setBirthDate(faker.date().birthday());
            book.addAuthor(author);
            bookRepo.save(book);
        }
        /*
        *
        *
        * book.setTitle(faker.book().title());
        book.setRelease_year(2006);
        book.setPages(2016);
        book.setPrice(100);
        book.addAuthor();

        Author author = new Author();
        author.setFirstName("Ayoub");
        author.setLastName("Daoui");
        author.getBooks().add(book);

        book.getAuthors().add(author);
        *
        *
        * */


        //bookRepo.save(book);
    }

}
