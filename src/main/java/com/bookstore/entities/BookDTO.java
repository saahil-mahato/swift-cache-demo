package com.bookstore.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDTO {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private double price;
}
