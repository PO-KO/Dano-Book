package com.dano.dano_book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dano.dano_book.entity.Book;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    @Query(
            value = "SELECT * FROM book_table WHERE LOWER(title) LIKE LOWER(CONCAT('%', :keyword, '%'));",
            nativeQuery = true)
    List<Book> searchBooks(String keyword);

}
