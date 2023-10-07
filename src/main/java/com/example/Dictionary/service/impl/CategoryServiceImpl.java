package com.example.Dictionary.service.impl;

import com.example.Dictionary.model.Category;
import com.example.Dictionary.repository.CategoryRepository;
import com.example.Dictionary.service.CategoryService;
import com.example.Dictionary.specifications.CategorySpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tinkoff.eacq.mma.dictionary.rest.model.Categories;
import ru.tinkoff.eacq.mma.dictionary.rest.model.CategoriesResponse;
import ru.tinkoff.eacq.mma.dictionary.rest.model.ShortCategories;
import ru.tinkoff.eacq.mma.dictionary.rest.model.ShortCategoriesResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private EntityManager entityManager;
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
    public Categories getCategories(@RequestParam(value = "code", required = false) String code, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "viewToClients", required = false) Boolean viewToClients, @RequestParam(value = "viewToManagers", required = false) Boolean viewToManagers, @RequestParam(value = "needsDocs", required = false) Boolean needsDocs, @RequestParam(value = "product", required = false) String product, @RequestParam(value = "risky", required = false) Boolean risky) {
        Specification<Category> spec = buildSpecification(code, name, viewToClients, viewToManagers, needsDocs, product, risky);

        List<Category> categoryList = categoryRepository.findAll(spec);
        if (Objects.isNull(categoryList) || categoryList.isEmpty()) {
//            return null;
            return new Categories(); // Возвращает пустой объект Categories
        }
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

        if (Objects.nonNull(code)) {
            spec = spec.and(CategorySpecifications.isEqual("categoryCode", code));
        }

        if (Objects.nonNull(name)) {
            spec = spec.and(CategorySpecifications.isEqual("categoryTab.name", name));
        }

        if (Objects.nonNull(viewToClients)) {
            spec = spec.and(CategorySpecifications.isEqual("viewToClients", viewToClients));
        }

        if (Objects.nonNull(viewToManagers)) {
            spec = spec.and(CategorySpecifications.isEqual("viewToManagers", viewToManagers));
        }

        if (Objects.nonNull(needsDocs)) {
            spec = spec.and(CategorySpecifications.isEqual("needsDocs", needsDocs));
        }

        if (Objects.nonNull(product)) {
            spec = spec.and(CategorySpecifications.isEqual("product", product));
        }

        if (Objects.nonNull(risky)) {
            spec = spec.and(CategorySpecifications.isEqual("risky", risky));
        }

        return spec;
    }

}



