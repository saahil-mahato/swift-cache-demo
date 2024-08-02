package com.bookstore.cache;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import org.swiftcache.cacherepository.ICacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of the {@link ICacheRepository} interface for managing {@link Book} entities.
 * <p>
 * This class provides a caching layer for {@link Book} entities using a {@link BookRepository}
 * to perform CRUD operations on the underlying data store.
 * </p>
 * <p>
 * It implements the caching interface methods to get, put, and remove {@link Book} instances
 * from the data store.
 * </p>
 */
@Component
public class BookCacheRepository implements ICacheRepository<String, Book> {

    private final BookRepository bookRepository;


    @Autowired
    public BookCacheRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves a {@link Book} from the data store using the given key.
     *
     * @param key the identifier of the {@link Book} to retrieve
     * @return the {@link Book} with the specified key, or {@code null} if no {@link Book}
     *         with the key exists
     */
    @Override
    public Book get(String key) {
        return bookRepository.findById(key).orElse(null);
    }

    /**
     * Adds or updates a {@link Book} in the data store with the given key.
     *
     * @param key the identifier of the {@link Book}
     * @param value the {@link Book} to store
     */
    @Override
    public void put(String key, Book value) {
        bookRepository.save(value);
    }

    /**
     * Removes a {@link Book} from the data store with the specified key.
     *
     * @param key the identifier of the {@link Book} to remove
     */
    @Override
    public void remove(String key) {
        bookRepository.deleteById(key);
    }
}
