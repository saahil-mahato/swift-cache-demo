package com.bookstore.postgresrepo;

import com.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Book} entities in a PostgreSQL database.
 *
 * <p>
 * This interface extends {@link JpaRepository}, providing CRUD operations
 * for the Book entity without the need for boilerplate code. It allows for
 * simple data access and manipulation using JPA.
 * </p>
 *
 * <p>
 * The primary key type for the Book entity is {@link String}.
 * </p>
 */
public interface BookRepositoryPostgreSQL extends JpaRepository<Book, String> {}