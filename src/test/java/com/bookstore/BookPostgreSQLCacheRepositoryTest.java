package com.bookstore;

import com.bookstore.entities.Book;
import com.bookstore.postgresrepo.BookPostgreSQLCacheRepository;
import com.bookstore.postgresrepo.BookRepositoryPostgreSQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.swiftcache.cacherepository.ICacheRepository;
import org.swiftcache.utils.TriFunction;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookPostgreSQLCacheRepositoryTest {

    @Mock
    private BookRepositoryPostgreSQL postgreSQLRepository;

    @InjectMocks
    private BookPostgreSQLCacheRepository postgreSQLCacheRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setId("1");
        book.setTitle("Test Book");
        book.setAuthor("Author");
        book.setIsbn("123456789");
        book.setPrice(9.99);
    }

    @Test
    void testGetBook() {
        when(postgreSQLRepository.findById("1")).thenReturn(Optional.of(book));
        Book retrievedBook = postgreSQLCacheRepository.get("1");
        assertEquals(book, retrievedBook);
    }

    @Test
    void testPutBook() {
        postgreSQLCacheRepository.put("1", book);
        verify(postgreSQLRepository, times(1)).save(book);
    }

    @Test
    void testRemoveBook() {
        postgreSQLCacheRepository.remove("1");
        verify(postgreSQLRepository, times(1)).deleteById("1");
    }

    @Test
    void testExecuteWithCache() {
        TriFunction<ICacheRepository<String, Book>, String, Book, Book> operation =
                (repo, key, value) -> {
                    value.setPrice(value.getPrice() + 2.0);
                    return value;
                };

        Book result = postgreSQLCacheRepository.executeWithCache(operation, book.getId(), book);
        assertEquals(11.99, result.getPrice()); // Price should be updated
    }
}
