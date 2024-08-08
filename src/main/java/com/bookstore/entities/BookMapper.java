package com.bookstore.entities;

/**
 * Utility class for mapping between the {@link Book} entity and the {@link BookDTO}.
 * This class provides static methods to convert a Book entity to a BookDTO
 * and vice versa.
 *
 * <p>
 * The class has a private constructor to prevent instantiation,
 * as it is intended to be a utility class with static methods only.
 * </p>
 */
public class BookMapper {

    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private BookMapper() {
        // Empty Constructor
    }

    /**
     * Converts a {@link Book} entity to a {@link BookDTO}.
     *
     * @param entity the Book entity to convert
     * @return a BookDTO containing the same data as the provided entity
     */
    public static BookDTO toDTO(Book entity) {
        BookDTO dto = new BookDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setAuthor(entity.getAuthor());
        dto.setIsbn(entity.getIsbn());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    /**
     * Converts a {@link BookDTO} to a {@link Book} entity.
     *
     * @param dto the BookDTO to convert
     * @return a Book entity containing the same data as the provided DTO
     */
    public static Book toEntity(BookDTO dto) {
        Book entity = new Book();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setIsbn(dto.getIsbn());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}