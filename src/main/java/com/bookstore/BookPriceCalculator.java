package com.bookstore;

import com.bookstore.entities.Book;
import org.swiftcache.cacherepository.ICacheRepository;


public class BookPriceCalculator {
    private BookPriceCalculator() {
        // Empty constructor
    }

    @SuppressWarnings("unchecked")
    public static <R> R calculatePrice(ICacheRepository<String, Book> repo, String key, Book book) {
        double price = book.getPrice();
        if (price < 10.0) {
            price += 2.0; // Add $2 to books priced less than $10
        }
        book.setPrice(price);
        repo.put(key, book);
        return (R) book;
    }
}