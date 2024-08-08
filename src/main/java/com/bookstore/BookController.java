package com.bookstore;

import com.bookstore.entities.Book;
import com.bookstore.entities.BookDTO;
import com.bookstore.entities.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link Book} entities.
 *
 * <p>
 * This class provides endpoints for retrieving, adding, removing, and calculating
 * the price of books. It uses the {@link BookCacheService} to handle the business logic
 * and data access.
 * </p>
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookCacheService bookCacheService;

    /**
     * Constructs a new instance of {@link BookController}.
     *
     * @param bookCacheService the service for managing book entities
     */
    @Autowired
    public BookController(BookCacheService bookCacheService) {
        this.bookCacheService = bookCacheService;
    }

    /**
     * Retrieves a {@link Book} entity by its unique identifier.
     *
     * @param id the unique identifier of the book
     * @return a ResponseEntity containing the Book if found, or an appropriate HTTP status
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable String id) {
        Book book = bookCacheService.getBook(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Adds a new {@link Book} entity.
     *
     * @param bookDTO the Data Transfer Object containing book details
     * @return a ResponseEntity containing the saved Book if successful, or an appropriate HTTP status
     */
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        Book savedBook = bookCacheService.putBook(book);

        if (savedBook != null) {
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Removes a {@link Book} entity by its unique identifier.
     *
     * @param id the unique identifier of the book to be removed
     * @return a ResponseEntity indicating the result of the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable String id) {
        bookCacheService.removeBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Calculates the price of a {@link Book} entity.
     *
     * @param bookDTO the Data Transfer Object containing book details
     * @return a ResponseEntity containing the updated Book with calculated price if successful, or an appropriate HTTP status
     */
    @PostMapping("/calculate-price")
    public ResponseEntity<Book> calculateBookPrice(@RequestBody BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        Book updatedBook = bookCacheService.calculateBookPrice(book);
        if (updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}