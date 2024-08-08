package com.bookstore.mongorepo;

import com.bookstore.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing {@link Book} entities in a MongoDB database.
 *
 * <p>
 * This interface extends {@link MongoRepository}, providing CRUD operations
 * for the Book entity without the need for boilerplate code. It allows for
 * simple data access and manipulation using MongoDB.
 * </p>
 *
 * <p>
 * The primary key type for the Book entity is {@link String}.
 * </p>
 */
public interface BookRepositoryMongoDB extends MongoRepository<Book, String> {}