package com.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * This class contains the {@link #main(String[])} method which starts the application by invoking
 * {@link SpringApplication#run(Class, String[])}. It is annotated with {@link SpringBootApplication}
 * to enable auto-configuration, component scanning, and configuration support.
 * </p>
 */
@SpringBootApplication
public class BookstoreApplication {

    /**
     * The main method which launches the Spring Boot application.
     * <p>
     * It calls {@link SpringApplication#run(Class, String[])} with the application class and command-line
     * arguments to bootstrap the application.
     * </p>
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }
}
