package com.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Objects;

/**
 * Represents a {@link Book} entity with properties such as id, title, author, and price.
 * <p>
 * This class is mapped to the database using JPA annotations. The ID of the book is generated
 * based on the title and author before persisting or updating the entity.
 * </p>
 */
@Entity
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private Double price;

    /**
     * Default constructor for JPA.
     */
    public Book() {}

    /**
     * Constructs a new {@link Book} with the given title, author, and price.
     * <p>
     * The ID is automatically generated based on the title and author.
     * </p>
     *
     * @param title  the title of the book
     * @param author the author of the book
     * @param price  the price of the book
     */
    public Book(String title, String author, Double price) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.id = generateId(title, author);
    }

    /**
     * Generates a unique ID for the book based on its title and author.
     * <p>
     * The ID is computed as the hexadecimal string of the hash code of the concatenated title and author.
     * </p>
     *
     * @param title  the title of the book
     * @param author the author of the book
     * @return the generated ID
     */
    public static String generateId(String title, String author) {
        return Integer.toHexString((title + author).hashCode());
    }

    /**
     * Generates the ID before the entity is persisted or updated in the database.
     * <p>
     * This method is invoked by JPA lifecycle callbacks.
     * </p>
     */
    @PrePersist
    @PreUpdate
    private void generateIdBeforePersist() {
        this.id = generateId(this.title, this.author);
    }

    /**
     * Returns the ID of the book.
     *
     * @return the ID of the book
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the book.
     *
     * @param id the new ID of the book
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the new title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the new author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Returns the price of the book.
     *
     * @return the price of the book
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the book.
     *
     * @param price the new price of the book
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Compares this book to another object for equality.
     * <p>
     * Two books are considered equal if they have the same ID.
     * </p>
     *
     * @param o the object to compare this book with
     * @return {@code true} if this book is equal to the specified object, otherwise {@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    /**
     * Returns the hash code value for this book.
     * <p>
     * The hash code is computed based on the book's ID.
     * </p>
     *
     * @return the hash code value for this book
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
