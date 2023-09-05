package com.example.Dictionary.service;

import com.example.Dictionary.model.CombinedCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.eacq.mma.dictionary.rest.model.Categories;
import ru.tinkoff.eacq.mma.dictionary.rest.model.CategoriesResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesAdapter {
    private final CategoryService categoryService;

    @Autowired
    public CategoriesAdapter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Categories getCategories(String code, String name, Boolean viewToClients, Boolean viewToManagers, Boolean needsDocs, String product, Boolean risky) {
        List<CombinedCategory> combinedCategories = categoryService.getCategories(code, name, viewToClients, viewToManagers, needsDocs, product, risky);

        Categories categories = new Categories();
        List<CategoriesResponse> categoriesResponses = new ArrayList<>();

        for (CombinedCategory combinedCategory : combinedCategories) {
            CategoriesResponse response = new CategoriesResponse();
            response.setCode(combinedCategory.getCode());
            response.setName(combinedCategory.getName());
            response.setNeedsDocs(combinedCategory.getNeedsDocs());
            response.setNeedsDocsReason(combinedCategory.getNeedsDocsReason());
            response.setNeedsDocsList(combinedCategory.getNeedsDocsList());
            response.setViewToClients(combinedCategory.getViewToClients());
            response.setViewToManagers(combinedCategory.getViewToManagers());
            response.setProduct(combinedCategory.getProduct());
            response.setRisky(combinedCategory.getRisky());

            categoriesResponses.add(response);
        }

        categories.setCategories(categoriesResponses);

        return categories;
    }
}

