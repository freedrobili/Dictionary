package com.example.Dictionary.specifications;

import com.example.Dictionary.model.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecifications {
    public static Specification<Category> isEqual(String key, Object value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get(key), value);
        };
    }
}



