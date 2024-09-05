package com.example.data.bo;


import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;

import java.util.Set;


@MappedEntity
public class Book {
    @Id
    @GeneratedValue(generator = "increment")
    private Integer id;
    private String title;
    @Relation(value = Relation.Kind.MANY_TO_MANY,  cascade = Relation.Cascade.ALL)
    @JoinTable(
            name = "book_author",
            joinColumns = @jakarta.persistence.JoinColumn(name = "book_id"),
            inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "author_id"))
    private Set<Author> authors;

    public Book() {
    }

    public Book(Integer id, String title, Set<Author> author) {
        this.id = id;
        this.title = title;
        this.authors = author;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + authors + '\'' +
                '}';
    }
}
