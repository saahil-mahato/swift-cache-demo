package com.bookstore.mongorepo;

import com.bookstore.entities.Book;
import org.springframework.stereotype.Component;
import org.swiftcache.cacherepository.ICacheRepository;
import org.swiftcache.utils.TriFunction;


@Component
public class BookMongoDBCacheRepository implements ICacheRepository<String, Book> {
    private final BookRepositoryMongoDB mongoDBRepository;

    public BookMongoDBCacheRepository(BookRepositoryMongoDB repository) {
        this.mongoDBRepository = repository;
    }

    @Override
    public Book get(String key) {
        return mongoDBRepository.findById(key).orElse(null);
    }

    @Override
    public void put(String key, Book value) {
        mongoDBRepository.save(value);
    }

    @Override
    public void remove(String key) {
        mongoDBRepository.deleteById(key);
    }

    @Override
    public <R> R executeWithCache(TriFunction<ICacheRepository<String, Book>, String, Book, R> operation, String key, Book value) {
        return operation.apply(this, key, value);
    }
}
