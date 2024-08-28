package com.example;

import com.example.data.bo.Book;
import com.example.data.repository.BookRepository;
import com.example.data.repository.support.BookPredicateSpecification;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;
import java.util.Arrays;
import java.util.List;

@MicronautTest
class BookRepositoryTest {

    @Inject
    BookRepository bookRepository;

    @BeforeEach
    void insertBooks() {
        bookRepository.deleteAll();
        // Create 5 book instances
        List<Book> books = Arrays.asList(
                new Book(null, "Title1", "Author1"),
                new Book(null, "Title2", "Author2"),
                new Book(null, "Title3", "Author3"),
                new Book(null, "Title4", "Author4"),
                new Book(null, "Title5", "Author5")
        );

        // Save books to the repository
        bookRepository.saveAll(books);
    }

    @Test
    void testUpperCriteriaQuery() {
        List<Book> books = bookRepository.findAll(BookPredicateSpecification.titleContains("Title"));
        Assertions.assertEquals(5, books.size());
    }

    @Test
    void testFieldSelector() {
        List<Book> books = bookRepository.findAll(BookPredicateSpecification.fieldSelector());
        Assertions.assertEquals(5, books.size());
        System.out.println(books);
    }
}