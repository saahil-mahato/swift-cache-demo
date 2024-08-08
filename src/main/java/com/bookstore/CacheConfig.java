package com.bookstore;

import com.bookstore.entities.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.swiftcache.SwiftCacheManager;
import org.swiftcache.cache.SwiftCache;
import org.swiftcache.cache.SwiftCacheConfig;

/**
 * Configuration class for setting up the cache for the bookstore application.
 *
 * <p>
 * This class defines the cache configuration and creates a {@link SwiftCache}
 * instance for managing the cache of {@link Book} entities.
 * </p>
 */
@Configuration
public class CacheConfig {

    /**
     * Creates a {@link SwiftCache} instance with the specified configuration.
     *
     * <p>
     * The cache configuration includes:
     * - Maximum size of 100 entries
     * - Least Recently Used (LRU) eviction strategy
     * - Read-through policy for fetching data from the cache
     * - Write-always policy for updating the cache on write operations
     * </p>
     *
     * @return the configured SwiftCache instance
     */
    @Bean
    public SwiftCache<String, Book> getSwiftCache() {
        // Create cache configuration
        SwiftCacheConfig config = new SwiftCacheConfig(
                100, // max size
                SwiftCacheConfig.LRU_EVICTION_STRATEGY, // eviction strategy
                SwiftCacheConfig.READ_THROUGH_POLICY, // read policy
                SwiftCacheConfig.WRITE_ALWAYS_POLICY // write policy
        );
        SwiftCacheManager<String, Book> manager = new SwiftCacheManager<>(config);
        return manager.getSwiftCache();
    }
}
