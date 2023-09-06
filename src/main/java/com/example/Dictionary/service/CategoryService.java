package com.example.Dictionary.service;

import ru.tinkoff.eacq.mma.dictionary.rest.model.Categories;
import ru.tinkoff.eacq.mma.dictionary.rest.model.ShortCategories;

import java.util.List;

public interface CategoryService {
    ShortCategories getCategoriesByProduct(String productName);

    Categories getCategories(String code, String name, Boolean viewToClients, Boolean viewToManagers, Boolean needsDocs, String product, Boolean risky);
}
