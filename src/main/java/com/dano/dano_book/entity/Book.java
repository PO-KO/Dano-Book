package com.dano.dano_book.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "book_table"
)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "bookId"
)
public class Book {

    @Id
    @GeneratedValue
    private Long bookId;
    private String title;
    private int release_year;
    private int pages;
    private double price;



    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private Set<Author> authors = new HashSet<>();

    public Book() {

    }

    public Book(String title, int release_year, int pages, double price, Set<Author> authors) {
        this.title = title;
        this.release_year = release_year;
        this.pages = pages;
        this.price = price;
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }
    public void removeAuthor(Author author) {
        this.getAuthors().remove(author);
        author.getBooks().remove(this);
    }
    public void removeAuthors() {
        for (Author author : new HashSet<>(authors)) {
            removeAuthor(author);
        }
    }


    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

}
