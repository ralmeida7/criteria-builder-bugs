package com.example.data.repository;

import com.example.data.bo.Author;
import com.example.data.bo.Book;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;
import io.micronaut.data.repository.jpa.criteria.QuerySpecification;

import java.util.List;

@JdbcRepository(dialect = Dialect.H2)
public interface AuthorRepository extends io.micronaut.data.repository.CrudRepository<Author, Integer>, JpaSpecificationExecutor<Author> {
    @Override
    @NonNull List<Author> findAll(@Nullable QuerySpecification<Author> spec);
}
