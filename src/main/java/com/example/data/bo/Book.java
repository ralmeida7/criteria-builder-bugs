package com.example.data.bo;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;

import java.util.Set;

@MappedEntity
public record Book(
        @Id @GeneratedValue(generator = "increment") @Nullable Integer id,
        String title,
        @Relation(value = Relation.Kind.MANY_TO_MANY, cascade = Relation.Cascade.ALL)
        @JoinTable(
                name = "book_author",
                joinColumns = @jakarta.persistence.JoinColumn(name = "book_id"),
                inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "author_id"))
        @Nullable Set<Author> authors
) {}