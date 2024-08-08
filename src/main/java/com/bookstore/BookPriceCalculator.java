package com.bookstore;

import com.bookstore.entities.Book;
import org.swiftcache.cacherepository.ICacheRepository;

/**
 * Utility class for calculating the price of {@link Book} entities.
 *
 * <p>
 * This class provides a static method to calculate the price of a book
 * based on specific business logic. It adjusts the price if it is below
 * a certain threshold and updates the book in the provided cache repository.
 * </p>
 */
public class BookPriceCalculator {

    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private BookPriceCalculator() {
        // Empty constructor
    }

    /**
     * Calculates the price of a {@link Book} entity.
     *
     * <p>
     * If the price of the book is less than $10, an additional $2 is added
     * to the price. The updated price is then saved back to the provided
     * cache repository.
     * </p>
     *
     * @param repo the cache repository where the book is stored
     * @param key the unique identifier of the book
     * @param book the Book entity for which to calculate the price
     * @param <R> the type of the result returned by the method
     * @return the updated Book entity with the calculated price
     */
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