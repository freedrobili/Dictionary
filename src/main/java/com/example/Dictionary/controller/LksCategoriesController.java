package com.example.Dictionary.controller;

import com.example.Dictionary.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.eacq.mma.dictionary.rest.controller.api.LksCategoriesApi;
import ru.tinkoff.eacq.mma.dictionary.rest.model.ShortCategories;

@RestController
public class LksCategoriesController implements LksCategoriesApi {
    private final CategoryService categoryService;

    @Autowired
    public LksCategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<ShortCategories> apiV1CategoriesProductNameGet( String productName) {
        return ResponseEntity.ok(categoryService.getCategoriesByProduct(productName));
    }
}
