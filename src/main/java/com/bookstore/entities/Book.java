package com.bookstore.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "books_postgres")
@Document(collection = "books_mongo")
public class Book {
    @Id
    private String id;

    private String title;
    private String author;
    private String isbn;
    private double price;
}
