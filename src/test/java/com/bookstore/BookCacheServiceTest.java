package com.bookstore;

import com.bookstore.entities.Book;
import com.bookstore.mongorepo.BookMongoDBCacheRepository;
import com.bookstore.postgresrepo.BookPostgreSQLCacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.swiftcache.cache.SwiftCache;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link BookCacheService} class.
 *
 * <p>
 * This class tests the functionality of the BookCacheService, including
 * methods for retrieving, saving, removing, and calculating the price of
 * Book entities. It uses Mockito for mocking dependencies and JUnit for
 * assertions.
 * </p>
 */
class BookCacheServiceTest {

    @Mock
    private SwiftCache<String, Book> cache;

    @Mock
    private BookPostgreSQLCacheRepository postgreSQLRepository;

    @Mock
    private BookMongoDBCacheRepository mongoDBRepository;

    @InjectMocks
    private BookCacheService bookCacheService;

    private Book book;

    /**
     * Sets up the test environment before each test method.
     * Initializes mocks and creates a sample Book entity.
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
     * Tests the retrieval of a Book entity.
     */
    @Test
    void testGetBook() {
        when(cache.get(postgreSQLRepository, "1")).thenReturn(book);
        Book retrievedBook = bookCacheService.getBook("1");
        assertEquals("Test Book", retrievedBook.getTitle());
    }

    /**
     * Tests the saving of a Book entity.
     */
    @Test
    void testPutBook() {
        String id = Integer.toHexString((book.getTitle() + book.getAuthor() + book.getIsbn()).hashCode());
        when(cache.put(mongoDBRepository, id, book)).thenReturn(book);
        Book savedBook = bookCacheService.putBook(book);
        assertNotNull(savedBook);
        assertEquals("Test Book", savedBook.getTitle());
    }

    /**
     * Tests the removal of a Book entity.
     */
    @Test
    void testRemoveBook() {
        doNothing().when(cache).remove(postgreSQLRepository, "1");
        bookCacheService.removeBook("1");
        verify(cache, times(1)).remove(postgreSQLRepository, "1");
    }

    /**
     * Tests the calculation of a Book's price.
     */
    @Test
    void testCalculateBookPrice() {
        Book newPriceBook = book;
        newPriceBook.setPrice(newPriceBook.getPrice() + 2.0);
        when(cache.executeWithCache(any(), any(), any(), any())).thenReturn(newPriceBook);
        Book updatedBook = bookCacheService.calculateBookPrice(book);
        assertEquals(11.99, updatedBook.getPrice()); // Price should be updated
    }

    /**
     * Tests the price calculation when the price is less than $10.
     */
    @Test
    void testCalculatePriceWhenPriceIsLessThanTen() {
        Book updatedBook = BookPriceCalculator.calculatePrice(postgreSQLRepository, book.getId(), book);
        assertEquals(11.99, updatedBook.getPrice()); // Price should be updated to 11.99
    }

    /**
     * Tests the price calculation when the price is $10 or more.
     */
    @Test
    void testCalculatePriceWhenPriceIsTenOrMore() {
        book.setPrice(10.00); // Price equal to $10
        Book updatedBook = BookPriceCalculator.calculatePrice(postgreSQLRepository, book.getId(), book);
        assertEquals(10.00, updatedBook.getPrice()); // Price should remain 10.00
    }
}
