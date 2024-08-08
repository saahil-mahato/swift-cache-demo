package com.bookstore;

import com.bookstore.entities.Book;
import com.bookstore.entities.BookDTO;
import com.bookstore.entities.BookMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMapperTest {

    @Test
    void testToDTO() {
        Book book = new Book();
        book.setId("1");
        book.setTitle("Test Book");
        book.setAuthor("Author");
        book.setIsbn("123456789");
        book.setPrice(9.99);

        BookDTO dto = BookMapper.toDTO(book);
        assertEquals(book.getId(), dto.getId());
        assertEquals(book.getTitle(), dto.getTitle());
    }

    @Test
    void testToEntity() {
        BookDTO dto = new BookDTO();
        dto.setId("1");
        dto.setTitle("Test Book");
        dto.setAuthor("Author");
        dto.setIsbn("123456789");
        dto.setPrice(9.99);

        Book book = BookMapper.toEntity(dto);
        assertEquals(dto.getId(), book.getId());
        assertEquals(dto.getTitle(), book.getTitle());
    }
}
