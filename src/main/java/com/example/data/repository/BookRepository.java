package com.example.data.repository;

import com.example.data.bo.Book;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.jpa.JpaSpecificationExecutor;
import io.micronaut.data.repository.jpa.criteria.QuerySpecification;

import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.H2)
public interface BookRepository extends io.micronaut.data.repository.CrudRepository<com.example.data.bo.Book, Integer>, JpaSpecificationExecutor<com.example.data.bo.Book> {
    @Override
    @NonNull List<Book> findAll(@Nullable QuerySpecification<Book> spec);

    @Override
    @Join(value="authors", type= Join.Type.LEFT_FETCH)
    Optional<Book> findById(Integer id);

    @SuppressWarnings("SqlResolve")
    @Query("DELETE FROM book_author WHERE book_id = :bookId")
    void deleteBookAuthorRelationsByBookId(Integer bookId);
}
