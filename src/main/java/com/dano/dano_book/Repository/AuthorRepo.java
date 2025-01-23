package com.dano.dano_book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dano.dano_book.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

    @Query(
            value = "SELECT * FROM author_table " +
                    "WHERE LOWER(first_name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(last_name) LIKE LOWER(CONCAT('%', :keyword, '%'));",
            nativeQuery = true)
    List<Author> searchAuthors(String keyword);

}
