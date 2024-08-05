package com.bookstore.model;

/**
 * Data Transfer Object (DTO) representing a book.
 * <p>
 * This DTO is used for transferring book data between layers of the application,
 * such as from the controller to the service layer.
 * </p>
 * <p>
 * The {@link BookDTO} class extends the {@link Book} class, inheriting its properties and methods.
 * It is designed to transfer data without exposing the internal structure of the {@link Book} entity.
 * </p>
 */
public class BookDTO extends Book {

    /**
     * Constructs a new {@link BookDTO} with the given title, author, and price.
     *
     * @param title  the title of the book
     * @param author the author of the book
     * @param price  the price of the book
     */
    public BookDTO(String title, String author, Double price) {
        super(title, author, price); // Call the constructor of the Book class
    }

    /**
     * Default constructor
     */
    public BookDTO() {
        // Empty constructor
    }

    // No need to redefine getters and setters, they are inherited from Book
}
