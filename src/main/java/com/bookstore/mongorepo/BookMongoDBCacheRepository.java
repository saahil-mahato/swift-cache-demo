package com.bookstore.mongorepo;

import com.bookstore.entities.Book;
import org.springframework.stereotype.Component;
import org.swiftcache.cacherepository.ICacheRepository;
import org.swiftcache.utils.TriFunction;

/**
 * Implementation of the {@link ICacheRepository} interface for managing
 * {@link Book} entities in a MongoDB repository with caching capabilities.
 *
 * <p>
 * This class serves as a bridge between the application and the MongoDB
 * repository, allowing for standard cache operations such as retrieval,
 * insertion, and deletion of Book entities.
 * </p>
 */
@Component
public class BookMongoDBCacheRepository implements ICacheRepository<String, Book> {

    private final BookRepositoryMongoDB mongoDBRepository;

    /**
     * Constructs a new instance of {@link BookMongoDBCacheRepository}.
     *
     * @param repository the MongoDB repository for Book entities
     */
    public BookMongoDBCacheRepository(BookRepositoryMongoDB repository) {
        this.mongoDBRepository = repository;
    }

    /**
     * Retrieves a {@link Book} entity by its unique identifier.
     *
     * @param key the unique identifier of the book
     * @return the Book entity if found, or null if not found
     */
    @Override
    public Book get(String key) {
        return mongoDBRepository.findById(key).orElse(null);
    }

    /**
     * Saves a {@link Book} entity in the MongoDB repository.
     *
     * @param key the unique identifier of the book
     * @param value the Book entity to be saved
     */
    @Override
    public void put(String key, Book value) {
        mongoDBRepository.save(value);
    }

    /**
     * Deletes a {@link Book} entity from the MongoDB repository by its unique identifier.
     *
     * @param key the unique identifier of the book to be deleted
     */
    @Override
    public void remove(String key) {
        mongoDBRepository.deleteById(key);
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