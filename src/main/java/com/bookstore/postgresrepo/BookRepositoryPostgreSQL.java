package com.bookstore.postgresrepo;

import com.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryPostgreSQL extends JpaRepository<Book, String> {}
