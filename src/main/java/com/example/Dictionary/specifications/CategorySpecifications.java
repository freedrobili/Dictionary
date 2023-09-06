package com.example.Dictionary.specifications;

import com.example.Dictionary.model.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecifications {
    public static Specification<Category> hasCategoryTabName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("categoryTab").get("name"), name);
    }
    public static Specification<Category> hasCategoryCode(String categoryCode) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("categoryCode"), categoryCode);
    }

    public static Specification<Category> hasProduct(String product) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("product"), product);
    }

    public static Specification<Category> hasViewToClients(Boolean viewToClients) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("viewToClients"), viewToClients);
    }

    public static Specification<Category> hasViewToManagers(Boolean viewToManagers) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("viewToManagers"), viewToManagers);
    }

    public static Specification<Category> hasNeedsDocs(Boolean needsDocs) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("needsDocs"), needsDocs);
    }

    public static Specification<Category> hasNeedsDocsReason(String needsDocsReason) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("needsDocsReason"), needsDocsReason);
    }

    public static Specification<Category> hasNeedsDocsList(String needsDocsList) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("needsDocsList"), needsDocsList);
    }

    public static Specification<Category> hasRisky(Boolean risky) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("risky"), risky);
    }
}

