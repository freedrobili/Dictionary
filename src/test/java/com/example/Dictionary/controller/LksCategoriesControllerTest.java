package com.example.Dictionary.controller;

import com.example.Dictionary.model.Category;
import com.example.Dictionary.model.CategoryTab;
import com.example.Dictionary.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ru.tinkoff.eacq.mma.dictionary.rest.model.ShortCategories;
import ru.tinkoff.eacq.mma.dictionary.rest.model.ShortCategoriesResponse;

import java.util.ArrayList;
import java.util.List;


public class LksCategoriesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new LksCategoriesController(categoryService)).build();

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void testApiV1CategoriesProductNameGet() throws Exception {
        String productName = "test_product_name";
        ShortCategories shortCategories = new ShortCategories();
        CategoryTab categoryTab = new CategoryTab();
        List<Category> categories = new ArrayList<>();
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
        categories.add(category);

        ModelMapper modelMapper = new ModelMapper();

        List<ShortCategoriesResponse> shortCategoriesResponses = modelMapper.map(categories, new TypeToken<List<ShortCategoriesResponse>>() {}.getType());
        shortCategories.setCategories(shortCategoriesResponses);

        when(categoryService.getCategoriesByProduct(productName)).thenReturn(shortCategories);

        mockMvc.perform(get("/api/v1/categories/{productName}", productName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.categories").exists())
                .andReturn();

        verify(categoryService, times(1)).getCategoriesByProduct(productName);
    }
}


