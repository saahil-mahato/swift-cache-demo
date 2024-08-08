package com.bookstore;

import com.bookstore.entities.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.swiftcache.cache.SwiftCache;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the {@link CacheConfig} class.
 *
 * <p>
 * This class tests the configuration of the cache in the Spring application context.
 * It verifies that the {@link SwiftCache} bean is correctly created and can be retrieved.
 * </p>
 */
class CacheConfigTest {

    /**
     * Tests the retrieval of the {@link SwiftCache} bean from the application context.
     *
     * <p>
     * This test initializes the Spring application context with the {@link CacheConfig}
     * configuration and asserts that the SwiftCache bean is not null, indicating that
     * it has been successfully created.
     * </p>
     */
    @Test
    @SuppressWarnings("unchecked")
    void testGetSwiftCache() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CacheConfig.class);
        SwiftCache<String, Book> cache = context.getBean(SwiftCache.class);
        assertNotNull(cache);
    }
}
