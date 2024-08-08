package com.bookstore;

import com.bookstore.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.swiftcache.cache.SwiftCache;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CacheConfigTest {

    @Test
    @SuppressWarnings("unchecked")
    void testGetSwiftCache() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CacheConfig.class);
        SwiftCache<String, Book> cache = context.getBean(SwiftCache.class);
        assertNotNull(cache);
    }
}