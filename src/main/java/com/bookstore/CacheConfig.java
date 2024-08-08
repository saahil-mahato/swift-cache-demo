package com.bookstore;

import com.bookstore.entities.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.swiftcache.SwiftCacheManager;
import org.swiftcache.cache.SwiftCache;
import org.swiftcache.cache.SwiftCacheConfig;

@Configuration
public class CacheConfig {
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
