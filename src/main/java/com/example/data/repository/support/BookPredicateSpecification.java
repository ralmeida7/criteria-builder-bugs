package com.example.data.repository.support;

import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;
import io.micronaut.data.repository.jpa.criteria.QuerySpecification;

public class BookPredicateSpecification {

    public static <T> PredicateSpecification<T> titleContains(String title) {
        return (root, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), "%" + title.toUpperCase() + "%");
    }

    public static <T> QuerySpecification<T> fieldSelector() {
        return (root, query, cb) -> {
            query.multiselect(root.get("title").alias("title"), root.get("author").alias("author"));
            return query.getRestriction();
        };
    }
}
