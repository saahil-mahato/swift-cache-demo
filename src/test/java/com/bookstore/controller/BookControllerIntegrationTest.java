package com.bookstore.controller;

import com.bookstore.BookstoreApplication;
import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

/**
 * Integration tests for the {@link BookController} class.
 * <p>
 * This test class uses {@link MockMvc} to perform HTTP requests and validate responses
 * for the various endpoints in the {@link BookController}. It includes tests for adding,
 * retrieving, removing books, and performing cache operations.
 * </p>
 */
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    private final MockMvc mockMvc;

    private final BookRepository bookRepository;

    private final BookService bookService;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, BookService bookService, BookRepository bookRepository) {
        this.mockMvc = mockMvc;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    /**
     * Sets up the test environment before each test.
     * <p>
     * This method clears the book repository and cache before each test to ensure a clean state.
     * </p>
     *
     * @throws Exception if an error occurs during setup
     */
    @BeforeEach
    void setUp() throws Exception {
        bookRepository.deleteAll(); // Clean up the database before each test
        mockMvc.perform(delete("/books/cache"))
                .andExpect(status().isOk());
    }

    /**
     * Tests the addition and retrieval of a {@link Book}.
     * <p>
     * This test performs the following steps:
     * <ol>
     *   <li>Adds a book via the POST /books endpoint.</li>
     *   <li>Extracts the book ID from the response.</li>
     *   <li>Retrieves the book via the GET /books/{id} endpoint.</li>
     *   <li>Verifies the book details match the added values.</li>
     * </ol>
     * </p>
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void testAddAndGetBook() throws Exception {
        String requestBody = "{\"title\":\"Test Book\",\"author\":\"Test Author\",\"price\":9.99}";

        // Add the book
        String response = mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Extract the ID from the response
        String bookId;
        try {
            bookId = extractIdFromResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract ID from response", e);
        }

        // Get the book by ID
        mockMvc.perform(get("/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test Book")))
                .andExpect(jsonPath("$.author", is("Test Author")))
                .andExpect(jsonPath("$.price", is(9.99)));
    }

    /**
     * Tests the removal of a {@link Book}.
     * <p>
     * This test performs the following steps:
     * <ol>
     *   <li>Adds a book via the {@link BookService}.</li>
     *   <li>Removes the book via the DELETE /books/{id} endpoint.</li>
     *   <li>Verifies the book is no longer retrievable.</li>
     * </ol>
     * </p>
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void testRemoveBook() throws Exception {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(9.99);

        bookService.addBook(book);

        mockMvc.perform(delete("/books/{id}", book.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/books/{id}", book.getId()))
                .andExpect(content().string(""));
    }

    /**
     * Tests cache operations for {@link Book} entities.
     * <p>
     * This test performs the following steps:
     * <ol>
     *   <li>Checks the initial cache size.</li>
     *   <li>Adds a book via the {@link BookService}.</li>
     *   <li>Checks the cache size after adding the book.</li>
     *   <li>Clears the cache via the DELETE /books/cache endpoint.</li>
     *   <li>Checks the cache size after clearing it.</li>
     * </ol>
     * </p>
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void testCacheOperations() throws Exception {
        mockMvc.perform(get("/books/cache/size"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));

        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(9.99);

        bookService.addBook(book);

        mockMvc.perform(get("/books/cache/size"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        mockMvc.perform(delete("/books/cache"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/books/cache/size"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    /**
     * Test context
     */
    @Test
    void testContext() {
        // This test will pass if the application context loads successfully
        assertThatNoException().isThrownBy(() -> BookstoreApplication.main(new String[] {}));
    }


    /**
     * Extracts the ID from the JSON response string.
     * <p>
     * This method uses {@link ObjectMapper} to parse the response and extract the ID field.
     * </p>
     *
     * @param response the JSON response string containing the book details
     * @return the extracted book ID
     * @throws Exception if an error occurs while parsing the response
     */
    private String extractIdFromResponse(String response) throws Exception {
        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Parse the response string to a JsonNode
        JsonNode jsonNode = objectMapper.readTree(response);

        // Extract the ID from the JsonNode
        if (jsonNode.has("id")) {
            return jsonNode.get("id").asText();
        } else {
            throw new RuntimeException("Response does not contain an 'id' field");
        }
    }


}
