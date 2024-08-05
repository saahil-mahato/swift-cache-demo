package com.bookstore.model;

/**
 * Data Transfer Object (DTO) representing a book.
 * <p>
 * This DTO is used for transferring book data between layers of the application,
 * such as from the controller to the service layer.
 * </p>
 */
public class BookDTO {
    private String title;
    private String author;
    private Double price;

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
