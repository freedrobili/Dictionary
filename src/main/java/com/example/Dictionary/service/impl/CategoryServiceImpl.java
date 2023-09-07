package com.example.Dictionary.service.impl;

import com.example.Dictionary.model.Category;
import com.example.Dictionary.repository.CategoryRepository;
import com.example.Dictionary.service.CategoryService;
import com.example.Dictionary.specifications.CategorySpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.tinkoff.eacq.mma.dictionary.rest.model.Categories;
import ru.tinkoff.eacq.mma.dictionary.rest.model.CategoriesResponse;
import ru.tinkoff.eacq.mma.dictionary.rest.model.ShortCategories;
import ru.tinkoff.eacq.mma.dictionary.rest.model.ShortCategoriesResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ShortCategories getCategoriesByProduct(String productName) {
        List<Category> categories = categoryRepository.findByProduct(productName);
        if (Objects.isNull(categories) || categories.isEmpty()) {
            return null;
        }
        ShortCategories shortCategories = new ShortCategories();
        List<ShortCategoriesResponse> shortCategoriesResponses = categories.stream()
                .map(this::buildShortCategories)
                .collect(Collectors.toList());

        shortCategories.setCategories(shortCategoriesResponses);
        return shortCategories;
    }

    @Override

    public Categories getCategories(String code, String name, Boolean viewToClients, Boolean viewToManagers, Boolean needsDocs, String product, Boolean risky) {
        Specification<Category> spec = buildSpecification(code, name, viewToClients, viewToManagers, needsDocs, product, risky);

        List<Category> categoryList = categoryRepository.findAll(spec);
        Categories categories = new Categories();
        List<CategoriesResponse> categoriesResponses = categoryList.stream()
                .map(this::buildCategoriesResponse)
                .collect(Collectors.toList());

        categories.setCategories(categoriesResponses);
        return categories;
    }

    private ShortCategoriesResponse buildShortCategories(Category category) {
        ShortCategoriesResponse response = new ShortCategoriesResponse();
        response.setCode(category.getCategoryCode());
        response.setName(category.getProduct());
        response.setNeedsDocs(category.getNeedsDocs());
        response.setNeedsDocsReason(category.getNeedsDocsReason());
        response.setNeedsDocsList(category.getNeedsDocsList());
        return response;
    }

    private CategoriesResponse buildCategoriesResponse(Category category) {
        CategoriesResponse response = new CategoriesResponse();
        response.setCode(category.getCategoryCode());
        response.setName(category.getCategoryTab().getName());
        response.setNeedsDocs(category.getNeedsDocs());
        response.setNeedsDocsReason(category.getNeedsDocsReason());
        response.setNeedsDocsList(category.getNeedsDocsList());
        response.setViewToClients(category.getViewToClients());
        response.setViewToManagers(category.getViewToManagers());
        response.setProduct(category.getProduct());
        response.setRisky(category.getRisky());

        return response;
    }

    private Specification<Category> buildSpecification(String code, String name, Boolean viewToClients, Boolean viewToManagers, Boolean needsDocs, String product, Boolean risky) {
        Specification<Category> spec = Specification.where(null);

        spec = spec.and(CategorySpecifications.isEqual("categpryCode", code))
                .and(CategorySpecifications.isEqual("categoryTab.name", name))
                .and(CategorySpecifications.isEqual("viewToClients", viewToClients))
                .and(CategorySpecifications.isEqual("viewToManagers", viewToManagers))
                .and(CategorySpecifications.isEqual("needsDocs", needsDocs))
                .and(CategorySpecifications.isEqual("product", product))
                .and(CategorySpecifications.isEqual("risky", risky));

        return spec;
    }
}



