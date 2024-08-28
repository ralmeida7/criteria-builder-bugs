package com.example.data.repository;

import com.example.data.bo.Book;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;
import io.micronaut.data.repository.jpa.criteria.QuerySpecification;

import java.util.List;

@Repository
public interface BookRepository extends io.micronaut.data.repository.CrudRepository<com.example.data.bo.Book, Integer>, JpaSpecificationExecutor<com.example.data.bo.Book> {
    @Override
    @NonNull List<Book> findAll(@Nullable QuerySpecification<Book> spec);
}
