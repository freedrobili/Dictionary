package com.example.Dictionary.specifications;

import com.example.Dictionary.model.CategoryTab;
import org.springframework.data.jpa.domain.Specification;

public class CategoryTabSpecifications {
    public static Specification<CategoryTab> hasCod(String code) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("code"), code);
    }

    public static Specification<CategoryTab> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }
}