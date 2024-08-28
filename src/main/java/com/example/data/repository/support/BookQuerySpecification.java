package com.example.data.repository.support;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.repository.jpa.criteria.QuerySpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BookQuerySpecification<T> implements QuerySpecification<T> {
    @Override
    public @Nullable Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
        query.multiselect(root.get("title"), root.get("author"));
        return null;
    }
}
