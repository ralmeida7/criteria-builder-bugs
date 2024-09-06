package com.example.data.bo;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Set;

@MappedEntity
public record Author(
        @Id @GeneratedValue(generator = "increment") @Nullable Integer id,
        String name,
        @Relation(value = Relation.Kind.MANY_TO_MANY, mappedBy = "authors")
        @Nullable Set<Book> books

) {}
