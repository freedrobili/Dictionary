package com.example.Dictionary.service;

import com.example.Dictionary.model.CombinedCategory;
import ru.tinkoff.eacq.mma.dictionary.rest.model.Categories;
import ru.tinkoff.eacq.mma.dictionary.rest.model.CategoriesResponse;
import ru.tinkoff.eacq.mma.dictionary.rest.model.ShortCategories;

import java.util.List;

public interface CategoryService {
    ShortCategories getCategoriesByProduct(String productName);

    List<CombinedCategory> getCategories(String code, String name, Boolean viewToClients, Boolean viewToManagers, Boolean needsDocs, String product, Boolean risky);
}
