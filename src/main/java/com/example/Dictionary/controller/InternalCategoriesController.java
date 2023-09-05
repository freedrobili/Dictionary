package com.example.Dictionary.controller;

import com.example.Dictionary.service.CategoriesAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.eacq.mma.dictionary.rest.controller.api.InternalCategoriesApi;
import ru.tinkoff.eacq.mma.dictionary.rest.model.*;

@RestController
public class InternalCategoriesController implements InternalCategoriesApi {

    private final CategoriesAdapter categoriesAdapter;

    @Autowired
    public InternalCategoriesController(CategoriesAdapter categoriesAdapter) {
        this.categoriesAdapter = categoriesAdapter;
    }

    @Override
    public ResponseEntity<Categories> internalApiV1CategoriesGet(String code, String name, Boolean viewToClients, Boolean viewToManagers, Boolean needsDocs, String product, Boolean risky) {
        Categories categories = categoriesAdapter.getCategories(code, name, viewToClients, viewToManagers, needsDocs, product, risky);
        return ResponseEntity.ok(categories);
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
