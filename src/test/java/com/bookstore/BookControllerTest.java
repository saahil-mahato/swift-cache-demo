package com.bookstore;

import com.bookstore.entities.Book;
import com.bookstore.entities.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link BookController} class.
 *
 * <p>
 * This class tests the functionality of the BookController, including
 * endpoints for retrieving, adding, removing, and calculating the price of
 * Book entities. It uses Mockito for mocking dependencies and JUnit for
 * assertions.
 * </p>
 */
class BookControllerTest {

    @Mock
    private BookCacheService bookCacheService;

    @InjectMocks
    private BookController bookController;

    private Book book;
    private BookDTO bookDTO;

    /**
     * Sets up the test environment before each test method.
     * Initializes mocks and creates sample Book and BookDTO instances.
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

        bookDTO = new BookDTO();
        bookDTO.setId("1");
        bookDTO.setTitle("Test Book");
        bookDTO.setAuthor("Author");
        bookDTO.setIsbn("123456789");
        bookDTO.setPrice(9.99);
    }

    /**
     * Tests the retrieval of a Book entity, returning a successful response.
     */
    @Test
    void testGetBookReturnsBook() {
        when(bookCacheService.getBook("1")).thenReturn(book);
        ResponseEntity<Book> response = bookController.getBook("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Book", Objects.requireNonNull(response.getBody()).getTitle());
    }

    /**
     * Tests the retrieval of a Book entity, returning an internal server error.
     */
    @Test
    void testGetBookReturnsInternalServerError() {
        when(bookCacheService.getBook("1")).thenReturn(null);
        ResponseEntity<Book> response = bookController.getBook("1");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Tests the addition of a Book entity, returning a successful response.
     */
    @Test
    void testAddBookReturnsBook() {
        when(bookCacheService.putBook(any(Book.class))).thenReturn(book);
        ResponseEntity<Book> response = bookController.addBook(bookDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Book", Objects.requireNonNull(response.getBody()).getTitle());
    }

    /**
     * Tests the addition of a Book entity, returning an internal server error.
     */
    @Test
    void testAddBookReturnsInternalServerError() {
        when(bookCacheService.putBook(any(Book.class))).thenReturn(null);
        ResponseEntity<Book> response = bookController.addBook(bookDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Tests the removal of a Book entity.
     */
    @Test
    void testRemoveBook() {
        ResponseEntity<Void> response = bookController.removeBook("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookCacheService, times(1)).removeBook("1");
    }

    /**
     * Tests the calculation of a Book's price, returning a successful response.
     */
    @Test
    void testCalculateBookPriceReturnsBook() {
        Book newBook = book;
        newBook.setPrice(newBook.getPrice() + 2.0);
        when(bookCacheService.calculateBookPrice(any(Book.class))).thenReturn(book);
        ResponseEntity<Book> response = bookController.calculateBookPrice(bookDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Book", Objects.requireNonNull(response.getBody()).getTitle());
    }

    /**
     * Tests the calculation of a Book's price, returning an internal server error.
     */
    @Test
    void testCalculateBookPriceReturnsInternalServerError() {
        when(bookCacheService.calculateBookPrice(any(Book.class))).thenReturn(null);
        ResponseEntity<Book> response = bookController.calculateBookPrice(bookDTO);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
