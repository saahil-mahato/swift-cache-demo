package com.bookstore.model;

/**
 * Mapper class for converting between {@link Book} entities and {@link BookDTO} objects.
 * <p>
 * This mapper provides methods to convert a {@link Book} entity to a {@link BookDTO} object
 * and vice versa.
 * </p>
 */
public class BookMapper {

    private BookMapper() {
        // Empty constructor
    }

    /**
     * Converts a {@link Book} entity to a {@link BookDTO} object.
     *
     * @param book the {@link Book} entity to be converted
     * @return the converted {@link BookDTO} object
     */
    public static BookDTO toDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPrice(book.getPrice());
        return bookDTO;
    }

    /**
     * Converts a {@link BookDTO} object to a {@link Book} entity.
     *
     * @param bookDTO the {@link BookDTO} object to be converted
     * @return the converted {@link Book} entity
     */
    public static Book toEntity(BookDTO bookDTO) {
        return new Book(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPrice());
    }
}
