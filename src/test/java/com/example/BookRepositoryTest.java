package com.example;

import com.example.data.bo.Author;
import com.example.data.bo.Book;
import com.example.data.repository.AuthorRepository;
import com.example.data.repository.BookRepository;
import com.example.data.repository.support.BookSpecification;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MicronautTest
class BookRepositoryTest {

    @Inject
    BookRepository bookRepository;
    @Inject
    AuthorRepository authorRepository;

//    @BeforeEach
    void insertBooks() {
        bookRepository.deleteAll();
        // Create 5 book instances
        List<Book> books = Arrays.asList(
                new Book(null, "Title1", null),
                new Book(null, "Title2", null),
                new Book(null, "Title3", null),
                new Book(null, "Title4", null),
                new Book(null, "Title5", null)
        );

        // Save books to the repository
        bookRepository.saveAll(books);
    }

    @Test
    void testUpperCriteriaQuery() {
        List<Book> books = bookRepository.findAll(BookSpecification.titleContains("Title"));
        Assertions.assertEquals(5, books.size());
    }

    @Test
    void testFieldSelector() {
        List<Book> books = bookRepository.findAll(BookSpecification.fieldSelector());
        Assertions.assertEquals(5, books.size());
        System.out.println(books);
    }

    @Test
    void testSaveBooksWithAuthors() {
        // Create author instances
        Author author1 = new Author(null, "Author1", null);

        Author author2 = new Author(null, "Author2", null);

        // Save authors to the repository
        author1 = authorRepository.save(author1);
        author2 = authorRepository.save(author2);

        // Create book instances with authors
        Book book1 = new Book(null, "Title1", new HashSet<>(Set.of(author1, author2)));

        Book book2 = new Book(null, "Title2", new HashSet<>(Set.of(author1)));

        // Save books to the repository
        book1 = bookRepository.save(book1);
        book2= bookRepository.save(book2);

        // Verify the books and authors are saved correctly
        Assertions.assertEquals(2, bookRepository.count());
        Assertions.assertEquals(2, authorRepository.count());

        Book retrievedBook1 = bookRepository.findById(book1.id()).orElse(null);
        Book retrievedBook2 = bookRepository.findById(book2.id()).orElse(null);

        Assertions.assertNotNull(retrievedBook1);
        Assertions.assertNotNull(retrievedBook2);
        Assertions.assertEquals(2, retrievedBook1.authors().size());
        Assertions.assertEquals(1, retrievedBook2.authors().size());

        retrievedBook1.authors().remove(retrievedBook1.authors().iterator().next());
        retrievedBook1 = new Book(retrievedBook1.id(), retrievedBook1.title(), retrievedBook1.authors());

        bookRepository.deleteBookAuthorRelationsByBookId(retrievedBook1.id());
        bookRepository.update(retrievedBook1);

        retrievedBook1 = bookRepository.findById(book1.id()).orElse(null);

        Assertions.assertEquals(1, retrievedBook1.authors().size());
        System.out.println(retrievedBook1.authors());
    }
}