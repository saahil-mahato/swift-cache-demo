package com.bookstore.mongorepo;


import com.bookstore.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepositoryMongoDB extends MongoRepository<Book, String> {}
