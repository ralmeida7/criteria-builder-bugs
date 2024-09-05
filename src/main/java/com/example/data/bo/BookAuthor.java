package com.example.data.bo;

import io.micronaut.data.annotation.MappedEntity;

@MappedEntity
public class BookAuthor {
    private Integer bookId;
    private Integer authorId;

    public BookAuthor() {
    }

    public BookAuthor(Integer bookId, Integer authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "BookAuthor{" +
                "bookId=" + bookId +
                ", authorId=" + authorId +
                '}';
    }
}
