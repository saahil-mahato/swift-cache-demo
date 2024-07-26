package com.bookstore.service;

import com.bookstore.model.Book;
import org.swiftcache.cache.SwiftCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private SwiftCache<String, Book> bookCache;

    public Book getBook(String id) {
        return bookCache.get(id);
    }

    public Book addBook(Book book) {
        bookCache.put(Book.generateId(book.getTitle(), book.getAuthor()), book);
        return book;
    }

    public void removeBook(String id) {
        bookCache.remove(id);
    }

    public long cacheSize() {
        return bookCache.size();
    }

    public void clearCache() {
        bookCache.clear();
    }
}
