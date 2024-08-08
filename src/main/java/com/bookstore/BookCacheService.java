package com.bookstore;

import com.bookstore.entities.Book;
import com.bookstore.mongorepo.BookMongoDBCacheRepository;
import com.bookstore.postgresrepo.BookPostgreSQLCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swiftcache.cache.SwiftCache;

/**
 * Service class for managing {@link Book} entities with caching capabilities,
 * using both PostgreSQL and MongoDB repositories.
 *
 * <p>
 * This class provides methods for retrieving, saving, and removing books,
 * as well as calculating the price of a book. It utilizes a cache to optimize
 * performance and reduce database access.
 * </p>
 */
@Service
public class BookCacheService {

    private final SwiftCache<String, Book> cache;

    private final BookPostgreSQLCacheRepository postgreSQLRepository;
    private final BookMongoDBCacheRepository mongoDBRepository;

    /**
     * Constructs a new instance of {@link BookCacheService}.
     *
     * @param cache the cache for storing book entities
     * @param bookRepositoryPostgreSQL the PostgreSQL repository for book entities
     * @param bookRepositoryMongoDB the MongoDB repository for book entities
     */
    @Autowired
    public BookCacheService(SwiftCache<String, Book> cache,
                            BookPostgreSQLCacheRepository bookRepositoryPostgreSQL,
                            BookMongoDBCacheRepository bookRepositoryMongoDB) {
        this.cache = cache;
        this.postgreSQLRepository = bookRepositoryPostgreSQL;
        this.mongoDBRepository = bookRepositoryMongoDB;
    }

    /**
     * Retrieves a {@link Book} entity from the cache or the PostgreSQL repository.
     *
     * @param id the unique identifier of the book
     * @return the Book entity
     */
    public Book getBook(String id) {
        return cache.get(postgreSQLRepository, id);
    }

    /**
     * Saves a {@link Book} entity to the cache and both PostgreSQL and MongoDB repositories.
     *
     * @param book the Book entity to be saved
     * @return the saved Book entity
     */
    public Book putBook(Book book) {
        book.setId(Integer.toHexString((book.getTitle() + book.getAuthor() + book.getIsbn()).hashCode()));
        cache.put(postgreSQLRepository, book.getId(), book);
        book = cache.put(mongoDBRepository, book.getId(), book);

        return book;
    }

    /**
     * Removes a {@link Book} entity from the cache and both PostgreSQL and MongoDB repositories.
     *
     * @param id the unique identifier of the book to be removed
     */
    public void removeBook(String id) {
        cache.remove(postgreSQLRepository, id);
        cache.remove(mongoDBRepository, id);
    }

    /**
     * Calculates the price of a {@link Book} entity using the {@link BookPriceCalculator}.
     *
     * @param book the Book entity for which to calculate the price
     * @return the Book entity with the calculated price
     */
    public Book calculateBookPrice(Book book) {
        book.setId(Integer.toHexString((book.getTitle() + book.getAuthor() + book.getIsbn()).hashCode()));

        return cache.executeWithCache(
                postgreSQLRepository,
                book.getId(),
                book,
                BookPriceCalculator::calculatePrice
        );
    }
}
