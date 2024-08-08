package com.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * The main application class for the Bookstore Spring Boot application.
 *
 * <p>
 * This class serves as the entry point for the application, initializing
 * the Spring context and enabling JPA repositories for PostgreSQL and
 * MongoDB repositories.
 * </p>
 *
 * <p>
 * The application is configured to scan the specified base packages for
 * repository interfaces and enables the necessary configurations for
 * both MongoDB and JPA.
 * </p>
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.bookstore.mongorepo")
@EnableJpaRepositories(basePackages = "com.bookstore.postgresrepo")
public class BookstoreApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }
}