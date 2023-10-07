package com.example.Dictionary.service.impl;

import com.example.Dictionary.model.Category;
import com.example.Dictionary.model.CategoryTab;
import com.example.Dictionary.repository.CategoryRepository;
import com.example.Dictionary.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.eacq.mma.dictionary.rest.model.Categories;
import ru.tinkoff.eacq.mma.dictionary.rest.model.CategoriesResponse;
import ru.tinkoff.eacq.mma.dictionary.rest.model.ShortCategories;
import ru.tinkoff.eacq.mma.dictionary.rest.model.ShortCategoriesResponse;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

public class CategoryServiceImplTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    public void initDb(){
        CategoryTab categoryTab = new CategoryTab();
        categoryTab.setCode("2144");
        categoryTab.setName("Спортивные Клубы");
        Category category = new Category(
                "2144",
                categoryTab,
                "safeDeal",
                true,
                true,
                true,
                "На сайте присутствуют товары известных брендов",
                "Вам необходимо предоставить документы, подтверждающие подлинность товаров (договор с официальным дистрибьютором или товарные накладные).",
                false
        );
        category = categoryRepository.save(category);

//        categoryRepository.saveAll(List.of(
//                new Category("code1", categoryTab, "ecom", true, true, true, "reason1", "list1", true),
//                new Category("cade2", categoryTab, "a2c", true, true, true, "reason2", "list2", true)
//        ));
    }

    @Test
    public void getCategoriesByProductTest() {
        String productName = "safeDeal";
        ShortCategories shortCategories = categoryService.getCategoriesByProduct(productName);
        assertNotNull(shortCategories);
        assertEquals(3, shortCategories.getCategories().size());

        ShortCategoriesResponse response = shortCategories.getCategories().get(0);
        assertEquals("2144", response.getCode());
        assertEquals(productName, response.getName());
    }

    @Test
    public void getCategoriesTest(){
//        initDb();
        Categories categories = categoryService.getCategories(null, null, true, true, true, "safeDeal", false);

        assertNotNull(categories);
        assertEquals(1, categories.getCategories().size());

        CategoriesResponse response = categories.getCategories().get(0);
        assertEquals("2144", response.getCode());
        assertEquals("Спортивные Клубы", response.getName());
        assertTrue(response.getViewToClients());
        assertTrue(response.getViewToManagers());
        assertFalse(response.getRisky());
    }
}
