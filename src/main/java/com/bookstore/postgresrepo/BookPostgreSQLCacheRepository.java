package com.bookstore.postgresrepo;

import com.bookstore.entities.Book;
import org.springframework.stereotype.Component;
import org.swiftcache.cacherepository.ICacheRepository;
import org.swiftcache.utils.TriFunction;

/**
 * Implementation of the {@link ICacheRepository} interface for managing
 * {@link Book} entities in a PostgreSQL repository with caching capabilities.
 *
 * <p>
 * This class serves as a bridge between the application and the PostgreSQL
 * repository, allowing for standard cache operations such as retrieval,
 * insertion, and deletion of Book entities.
 * </p>
 */
@Component
public class BookPostgreSQLCacheRepository implements ICacheRepository<String, Book> {

    private final BookRepositoryPostgreSQL postgreSQLRepository;

    /**
     * Constructs a new instance of {@link BookPostgreSQLCacheRepository}.
     *
     * @param repository the PostgreSQL repository for Book entities
     */
    public BookPostgreSQLCacheRepository(BookRepositoryPostgreSQL repository) {
        this.postgreSQLRepository = repository;
    }

    /**
     * Retrieves a {@link Book} entity by its unique identifier.
     *
     * @param key the unique identifier of the book
     * @return the Book entity if found, or null if not found
     */
    @Override
    public Book get(String key) {
        return postgreSQLRepository.findById(key).orElse(null);
    }

    /**
     * Saves a {@link Book} entity in the PostgreSQL repository.
     *
     * @param key the unique identifier of the book
     * @param value the Book entity to be saved
     */
    @Override
    public void put(String key, Book value) {
        postgreSQLRepository.save(value);
    }

    /**
     * Deletes a {@link Book} entity from the PostgreSQL repository by its unique identifier.
     *
     * @param key the unique identifier of the book to be deleted
     */
    @Override
    public void remove(String key) {
        postgreSQLRepository.deleteById(key);
    }

    /**
     * Executes a given operation with the cache, allowing for custom cache logic.
     *
     * @param operation the operation to be executed, defined as a {@link TriFunction}
     * @param key the unique identifier of the book
     * @param value the Book entity to be processed
     * @param <R> the type of the result returned by the operation
     * @return the result of the operation
     */
    @Override
    public <R> R executeWithCache(TriFunction<ICacheRepository<String, Book>, String, Book, R> operation, String key, Book value) {
        return operation.apply(this, key, value);
    }
}
