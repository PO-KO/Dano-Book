package com.dano.dano_book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dano.dano_book.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

}
