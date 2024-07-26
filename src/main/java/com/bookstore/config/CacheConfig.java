package com.bookstore.config;

import com.bookstore.cache.BookCacheRepository;
import com.bookstore.model.Book;
import org.swiftcache.SwiftCacheManager;
import org.swiftcache.cache.SwiftCache;
import org.swiftcache.cache.SwiftCacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the caching mechanism using {@link SwiftCache}.
 * <p>
 * This class provides the configuration for {@link SwiftCache} by defining the cache properties
 * and initializing the {@link SwiftCacheManager} with a custom {@link BookCacheRepository}.
 * </p>
 */
@Configuration
public class CacheConfig {

    /**
     * Creates and configures a {@link SwiftCache} bean for caching {@link Book} entities.
     * <p>
     * The configuration includes setting the maximum cache size to 100 entries, using
     * "LRU" (Least Recently Used) eviction policy, and specifying "ReadThrough" and
     * "WriteAlways" strategies for cache reads and writes.
     * </p>
     *
     * @param bookCacheRepository the {@link BookCacheRepository} to be used by the cache manager
     * @return a {@link SwiftCache} instance configured with the specified properties
     */
    @Bean
    public SwiftCache<String, Book> getSwiftCache(BookCacheRepository bookCacheRepository) {
        // Create SwiftCacheConfig with properties
        SwiftCacheConfig config = new SwiftCacheConfig(100, "LRU", "ReadThrough", "WriteAlways");

        // Create SwiftCacheManager with config and repository
        SwiftCacheManager<String, Book> cacheManager = new SwiftCacheManager<>(config, bookCacheRepository);

        return cacheManager.getSwiftCache();
    }
}
