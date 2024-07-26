package com.bookstore.repository;

import com.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing {@link Book} entities.
 * <p>
 * This interface extends {@link JpaRepository} and provides CRUD operations for {@link Book} entities
 * with a primary key of type {@link String}.
 * </p>
 * <p>
 * By extending {@link JpaRepository}, it inherits standard data access methods such as save, findAll,
 * findById, delete, and more. Custom query methods can be defined if needed.
 * </p>
 */
@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
