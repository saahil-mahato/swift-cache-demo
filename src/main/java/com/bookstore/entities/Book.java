package com.bookstore.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a book entity in the bookstore application.
 * This class is annotated to support both PostgreSQL and MongoDB.
 * It contains attributes that define the properties of a book,
 * such as its title, author, ISBN, and price.
 *
 * <p>
 * The class is mapped to a PostgreSQL table named "books_postgres"
 * and a MongoDB collection named "books_mongo".
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     Book book = new Book();
 *     book.setId("1");
 *     book.setTitle("Effective Java");
 *     book.setAuthor("Joshua Bloch");
 *     book.setIsbn("978-0134686097");
 *     book.setPrice(45.00);
 * </pre>
 * </p>
 */
@Setter
@Getter
@Entity
@Table(name = "books_postgres")
@Document(collection = "books_mongo")
public class Book {

    /**
     * The unique identifier for the book.
     * This field serves as the primary key in the PostgreSQL database
     * and as the unique identifier in the MongoDB collection.
     */
    @Id
    private String id;

    /**
     * The title of the book.
     */
    private String title;

    /**
     * The author of the book.
     */
    private String author;

    /**
     * The International Standard Book Number (ISBN) that uniquely identifies the book.
     */
    private String isbn;

    /**
     * The price of the book.
     */
    private double price;
}