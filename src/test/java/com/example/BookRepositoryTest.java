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
        Author author1 = new Author();
        author1.setName("Author1");

        Author author2 = new Author();
        author2.setName("Author2");

        // Save authors to the repository
        authorRepository.save(author1);
        authorRepository.save(author2);

        // Create book instances with authors
        Book book1 = new Book();
        book1.setTitle("Title1");
        book1.setAuthors(new HashSet<>(Set.of(author1, author2)));

        Book book2 = new Book();
        book2.setTitle("Title2");
        book2.setAuthors(new HashSet<>(Set.of(author1)));

        // Save books to the repository
        bookRepository.save(book1);
        bookRepository.save(book2);

        // Verify the books and authors are saved correctly
        Assertions.assertEquals(2, bookRepository.count());
        Assertions.assertEquals(2, authorRepository.count());

        Book retrievedBook1 = bookRepository.findById(book1.getId()).orElse(null);
        Book retrievedBook2 = bookRepository.findById(book2.getId()).orElse(null);

        Assertions.assertNotNull(retrievedBook1);
        Assertions.assertNotNull(retrievedBook2);
        Assertions.assertEquals(2, retrievedBook1.getAuthors().size());
        Assertions.assertEquals(1, retrievedBook2.getAuthors().size());

        retrievedBook1.getAuthors().remove(retrievedBook1.getAuthors().iterator().next());

        bookRepository.deleteBookAuthorRelationsByBookId(retrievedBook1.getId());
        bookRepository.update(retrievedBook1);

        retrievedBook1 = bookRepository.findById(book1.getId()).orElse(null);

        Assertions.assertEquals(1, retrievedBook1.getAuthors().size());
        System.out.println(retrievedBook1.getAuthors());
    }
}