package com.bookstore.service;

import com.bookstore.model.Book;
import org.swiftcache.cache.SwiftCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link BookService} class.
 * <p>
 * This test class uses Mockito to mock the {@link SwiftCache} dependency and verifies the behavior
 * of the {@link BookService} methods. It includes tests for retrieving, adding, removing books,
 * and performing cache operations.
 * </p>
 */
public class BookServiceUnitTest {

    @Mock
    private SwiftCache<String, Book> bookCache;

    @InjectMocks
    private BookService bookService;

    /**
     * Initializes Mockito annotations before each test.
     * <p>
     * This method sets up the mocks and injects them into the {@link BookService} instance.
     * </p>
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the retrieval of a {@link Book} from the cache.
     * <p>
     * This test performs the following steps:
     * <ol>
     *   <li>Sets up a mock book and configures the cache to return this book when requested by ID.</li>
     *   <li>Calls the {@link BookService#getBook(String)} method.</li>
     *   <li>Verifies that the retrieved book matches the expected values.</li>
     * </ol>
     * </p>
     */
    @Test
    void testGetBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setId(Book.generateId(book.getTitle(), book.getAuthor()));
        book.setPrice(9.99);

        when(bookCache.get(book.getId())).thenReturn(book);

        Book retrievedBook = bookService.getBook(book.getId());

        assertNotNull(retrievedBook);
        assertEquals("Test Book", retrievedBook.getTitle());
        assertEquals("Test Author", retrievedBook.getAuthor());
        assertEquals(9.99, retrievedBook.getPrice());
    }

    /**
     * Tests the addition of a {@link Book} to the cache.
     * <p>
     * This test performs the following steps:
     * <ol>
     *   <li>Sets up a book with known properties.</li>
     *   <li>Calls the {@link BookService#addBook(Book)} method.</li>
     *   <li>Verifies that the {@link SwiftCache#put(String, Book)} method was called with the correct parameters.</li>
     * </ol>
     * </p>
     */
    @Test
    void testAddBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setId(Book.generateId(book.getTitle(), book.getAuthor()));
        book.setPrice(9.99);

        bookService.addBook(book);

        verify(bookCache, times(1)).put(book.getId(), book);
    }

    /**
     * Tests the removal of a {@link Book} from the cache.
     * <p>
     * This test performs the following steps:
     * <ol>
     *   <li>Calls the {@link BookService#removeBook(String)} method with a known book ID.</li>
     *   <li>Verifies that the {@link SwiftCache#remove(String)} method was called with the correct ID.</li>
     * </ol>
     * </p>
     */
    @Test
    void testRemoveBook() {
        bookService.removeBook(Book.generateId("Test Book", "Test Author"));

        verify(bookCache, times(1)).remove(Book.generateId("Test Book", "Test Author"));
    }

    /**
     * Tests the retrieval of the cache size.
     * <p>
     * This test performs the following steps:
     * <ol>
     *   <li>Configures the cache to return a specific size when queried.</li>
     *   <li>Calls the {@link BookService#cacheSize()} method.</li>
     *   <li>Verifies that the returned size matches the expected value.</li>
     * </ol>
     * </p>
     */
    @Test
    void testCacheSize() {
        when(bookCache.size()).thenReturn(1L);

        long size = bookService.cacheSize();

        assertEquals(1L, size);
    }

    /**
     * Tests clearing the cache.
     * <p>
     * This test performs the following steps:
     * <ol>
     *   <li>Calls the {@link BookService#clearCache()} method.</li>
     *   <li>Verifies that the {@link SwiftCache#clear()} method was called exactly once.</li>
     * </ol>
     * </p>
     */
    @Test
    void testClearCache() {
        bookService.clearCache();

        verify(bookCache, times(1)).clear();
    }
}
