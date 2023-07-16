package com.example.dataJPA.repository;

import com.example.dataJPA.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
