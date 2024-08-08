package com.bookstore.postgresrepo;

import com.bookstore.entities.Book;
import org.springframework.stereotype.Component;
import org.swiftcache.cacherepository.ICacheRepository;
import org.swiftcache.utils.TriFunction;


@Component
public class BookPostgreSQLCacheRepository implements ICacheRepository<String, Book> {
    private final BookRepositoryPostgreSQL postgreSQLRepository;

    public BookPostgreSQLCacheRepository(BookRepositoryPostgreSQL repository) {
        this.postgreSQLRepository = repository;
    }

    @Override
    public Book get(String key) {
        return postgreSQLRepository.findById(key).orElse(null);
    }

    @Override
    public void put(String key, Book value) {
        postgreSQLRepository.save(value);
    }

    @Override
    public void remove(String key) {
        postgreSQLRepository.deleteById(key);
    }

    @Override
    public <R> R executeWithCache(TriFunction<ICacheRepository<String, Book>, String, Book, R> operation, String key, Book value) {
        return operation.apply(this, key, value);
    }
}
