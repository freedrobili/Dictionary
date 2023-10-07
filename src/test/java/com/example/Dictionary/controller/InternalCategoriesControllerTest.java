package com.example.Dictionary.controller;

import com.example.Dictionary.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.verify;
import ru.tinkoff.eacq.mma.dictionary.rest.model.Categories;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InternalCategoriesController.class)
public class InternalCategoriesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @InjectMocks
    private InternalCategoriesController internalCategoriesController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInternalApiV1CategoriesGet() throws Exception {

        String code = "2211";
        String name = "test_name";
        Boolean viewToClients = true;
        Boolean viewToManagers = false;
        Boolean needsDocs = true;
        String product = "test_product";
        Boolean risky = false;

        Categories mockCategories = new Categories(); // Здесь создайте объект Categories с данными, которые вы хотите вернуть
        when(categoryService.getCategories(code, name, viewToClients, viewToManagers, needsDocs, product, risky)).thenReturn(mockCategories);

        mockMvc.perform(get("/internal/api/v1/categories")
                        .param("code", code)
                        .param("name", name)
                        .param("viewToClients", String.valueOf(viewToClients))
                        .param("viewToManagers", String.valueOf(viewToManagers))
                        .param("needsDocs", String.valueOf(needsDocs))
                        .param("product", product)
                        .param("risky", String.valueOf(risky)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
        verify(categoryService).getCategories(code, name, viewToClients, viewToManagers, needsDocs, product, risky);
    }
}
