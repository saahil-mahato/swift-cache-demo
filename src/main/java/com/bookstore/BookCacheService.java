package com.bookstore;

import com.bookstore.entities.Book;
import com.bookstore.mongorepo.BookMongoDBCacheRepository;
import com.bookstore.postgresrepo.BookPostgreSQLCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swiftcache.cache.SwiftCache;
import org.swiftcache.cacherepository.ICacheRepository;


@Service
public class BookCacheService {
    private final SwiftCache<String, Book> cache;

    private final BookPostgreSQLCacheRepository postgreSQLRepository;
    private final BookMongoDBCacheRepository mongoDBRepository;


    @Autowired
    public BookCacheService(SwiftCache<String, Book> cache,
                            BookPostgreSQLCacheRepository bookRepositoryPostgreSQL,
                            BookMongoDBCacheRepository bookRepositoryMongoDB) {
        this.cache = cache;
        this.postgreSQLRepository = bookRepositoryPostgreSQL;
        this.mongoDBRepository = bookRepositoryMongoDB;
    }


    public Book getBook(String id) {
        return cache.get(postgreSQLRepository, id);
    }


    public Book putBook(Book book) {
        book.setId(Integer.toHexString((book.getTitle() + book.getAuthor() + book.getIsbn()).hashCode()));
        cache.put(postgreSQLRepository, book.getId(), book);
        book = cache.put(mongoDBRepository, book.getId(), book);

        return book;
    }


    public void removeBook(String id) {
        cache.remove(postgreSQLRepository, id);
        cache.remove(mongoDBRepository, id);
    }

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
