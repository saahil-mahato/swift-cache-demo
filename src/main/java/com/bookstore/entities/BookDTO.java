package com.bookstore.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for the Book entity.
 * This class represents a simplified version of the Book class,
 * containing only the necessary fields for data transfer purposes.
 * It is used to transfer book data between layers of the application,
 * such as from the controller to the service layer.
 *
 * <p>
 * The class uses Lombok annotations to automatically generate
 * getter and setter methods for the fields.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     BookDTO bookDTO = new BookDTO();
 *     bookDTO.setId("1");
 *     bookDTO.setTitle("Effective Java");
 *     bookDTO.setAuthor("Joshua Bloch");
 *     bookDTO.setIsbn("978-0134686097");
 *     bookDTO.setPrice(45.00);
 * </pre>
 * </p>
 */
@Setter
@Getter
public class BookDTO {
    /**
     * The unique identifier for the book.
     */
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