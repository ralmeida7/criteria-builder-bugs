package com.example;

import com.example.data.bo.Book;
import com.example.data.repository.BookRepository;
import com.example.data.repository.support.BookPredicateSpecification;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;
import java.util.Arrays;
import java.util.List;

@MicronautTest
class BookRepositoryTest {

    @Inject
    BookRepository bookRepository;

    @Test
    void testAddBooks() {
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

        // Verify the count of books in the repository
        Assertions.assertEquals(5, bookRepository.count());

        // Verify the details of the books
        List<Book> savedBooks = (List<Book>) bookRepository.findAll();
        for (int i = 0; i < books.size(); i++) {
            Assertions.assertEquals(books.get(i).getTitle(), savedBooks.get(i).getTitle());
            Assertions.assertEquals(books.get(i).getAuthor(), savedBooks.get(i).getAuthor());
        }
    }

    @Test
    void testUpperCriteriaQuery() {
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

        List<Book> saveAll = bookRepository.findAll(BookPredicateSpecification.titleContains("Title"));
        Assertions.assertEquals(5, saveAll.size());

        saveAll = bookRepository.findAll(BookPredicateSpecification.fieldSelector());

        System.out.println(saveAll);

    }
}