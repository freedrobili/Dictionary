package com.example.Dictionary.controller;

import com.example.Dictionary.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.eacq.mma.dictionary.rest.controller.api.InternalCategoriesApi;
import ru.tinkoff.eacq.mma.dictionary.rest.model.*;

@RestController
public class InternalCategoriesController implements InternalCategoriesApi {

    private final CategoryService categoryService;

    public InternalCategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<Categories> internalApiV1CategoriesGet(String code, String name, Boolean viewToClients, Boolean viewToManagers, Boolean needsDocs, String product, Boolean risky) {
        return ResponseEntity.ok(categoryService.getCategories(code, name, viewToClients, viewToManagers, needsDocs, product, risky));
    }

    @Override
    public ResponseEntity<CategoriesResponse> internalApiV1CategoriesProductNamePost(String productName, CategoriesCreateRequest categoriesCreateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<CategoriesSocialPropertiesResponse> internalApiV1SocialCategoriesCategoryCodeGet(String categoryCode) {
        return null;
    }
}
