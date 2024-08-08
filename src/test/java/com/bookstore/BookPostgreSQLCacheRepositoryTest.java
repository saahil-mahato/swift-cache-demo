package com.bookstore;

import com.bookstore.entities.Book;
import com.bookstore.postgresrepo.BookPostgreSQLCacheRepository;
import com.bookstore.postgresrepo.BookRepositoryPostgreSQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.swiftcache.cacherepository.ICacheRepository;
import org.swiftcache.utils.TriFunction;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link BookPostgreSQLCacheRepository} class.
 *
 * <p>
 * This class tests the functionality of the BookPostgreSQLCacheRepository,
 * which provides caching capabilities for {@link Book} entities stored
 * in a PostgreSQL repository. It uses Mockito for mocking dependencies and
 * JUnit for assertions.
 * </p>
 */
class BookPostgreSQLCacheRepositoryTest {

    @Mock
    private BookRepositoryPostgreSQL postgreSQLRepository;

    @InjectMocks
    private BookPostgreSQLCacheRepository postgreSQLCacheRepository;

    private Book book;

    /**
     * Sets up the test environment before each test method.
     * Initializes mocks and creates a sample Book instance.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setId("1");
        book.setTitle("Test Book");
        book.setAuthor("Author");
        book.setIsbn("123456789");
        book.setPrice(9.99);
    }

    /**
     * Tests the retrieval of a Book entity from the PostgreSQL repository.
     */
    @Test
    void testGetBook() {
        when(postgreSQLRepository.findById("1")).thenReturn(Optional.of(book));
        Book retrievedBook = postgreSQLCacheRepository.get("1");
        assertEquals(book, retrievedBook);
    }

    /**
     * Tests the saving of a Book entity to the PostgreSQL repository.
     */
    @Test
    void testPutBook() {
        postgreSQLCacheRepository.put("1", book);
        verify(postgreSQLRepository, times(1)).save(book);
    }

    /**
     * Tests the removal of a Book entity from the PostgreSQL repository.
     */
    @Test
    void testRemoveBook() {
        postgreSQLCacheRepository.remove("1");
        verify(postgreSQLRepository, times(1)).deleteById("1");
    }

    /**
     * Tests the execution of a custom operation with the cache.
     */
    @Test
    void testExecuteWithCache() {
        TriFunction<ICacheRepository<String, Book>, String, Book, Book> operation =
                (repo, key, value) -> {
                    value.setPrice(value.getPrice() + 2.0);
                    return value;
                };

        Book result = postgreSQLCacheRepository.executeWithCache(operation, book.getId(), book);
        assertEquals(11.99, result.getPrice()); // Price should be updated
    }
}
