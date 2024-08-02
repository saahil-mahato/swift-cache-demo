package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link Book} entities.
 * <p>
 * This class provides endpoints to perform CRUD operations on books and manage the cache.
 * </p>
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Retrieves a {@link Book} by its identifier.
     *
     * @param id the identifier of the {@link Book} to retrieve
     * @return the {@link Book} with the specified identifier
     */
    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id) {
        return bookService.getBook(id);
    }

    /**
     * Adds a new {@link Book} to the system.
     *
     * @param book the {@link Book} to add
     * @return the added {@link Book}
     */
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    /**
     * Removes a {@link Book} from the system by its identifier.
     *
     * @param id the identifier of the {@link Book} to remove
     */
    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable String id) {
        bookService.removeBook(id);
    }

    /**
     * Retrieves the current size of the cache.
     *
     * @return the number of entries currently in the cache
     */
    @GetMapping("/cache/size")
    public long cacheSize() {
        return bookService.cacheSize();
    }

    /**
     * Clears all entries from the cache.
     */
    @DeleteMapping("/cache")
    public void clearCache() {
        bookService.clearCache();
    }
}
