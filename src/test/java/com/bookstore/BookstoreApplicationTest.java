package com.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class BookstoreApplicationTest {

    @Test
    void contextLoads() {
        // This test will check if the application can start without throwing exceptions
        String[] args = {};

        // Assert that no exception is thrown when running the main method
        assertDoesNotThrow(() -> BookstoreApplication.main(args), "Application failed to start");
    }
}