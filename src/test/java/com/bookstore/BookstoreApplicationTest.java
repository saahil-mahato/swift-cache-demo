package com.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Unit tests for the {@link BookstoreApplication} class.
 *
 * <p>
 * This class contains tests to verify that the Spring Boot application
 * context loads successfully without throwing any exceptions.
 * </p>
 */
@SpringBootTest
class BookstoreApplicationTest {

    /**
     * Tests that the application context loads successfully.
     *
     * <p>
     * This test checks if the main application class can be started
     * without throwing exceptions, ensuring that the application is
     * properly configured.
     * </p>
     */
    @Test
    void contextLoads() {
        // This test will check if the application can start without throwing exceptions
        String[] args = {};

        // Assert that no exception is thrown when running the main method
        assertDoesNotThrow(() -> BookstoreApplication.main(args), "Application failed to start");
    }
}
