package com.epam.cashier.controller;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.ProductService;
import com.epam.cashier.controller.service.test.config.TestConfig;
import com.epam.cashier.controller.service.test.util.TestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.epam.cashier.controller.service.test.util.TestDataUtil.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@Import(TestConfig.class)
class ProductControllerTest {
    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllProductsTest() throws Exception {
        List<ProductDto> listProductsDtoes = TestDataUtil.createListProductsDtoes();
        when(productService.listProduct()).thenReturn(listProductsDtoes);

        mockMvc.perform(get("/api/v1/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createProductTest() throws Exception {
        ProductDto productDto = TestDataUtil.createProductDto();
        when(productService.createProduct(productDto)).thenReturn(productDto);

        mockMvc.perform(post("/api/v1/product")
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(PRODUCT_CODE));
    }

    @Test
    void updateProductTest() throws Exception {
        ProductDto productDto = TestDataUtil.createProductDto();
        when(productService.updateProduct(PRODUCT_CODE,productDto)).thenReturn(productDto);

        mockMvc.perform(patch("/api/v1/product/" + PRODUCT_CODE)
                .contentType(
                        MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(PRODUCT_CODE));
    }

    @Test
    void getProductTest() throws Exception{
        ProductDto productDto = TestDataUtil.createProductDto();
      when(productService.getProductById(PRODUCT_ID)).thenReturn(productDto);

        mockMvc.perform(get("/api/v1/product/" + PRODUCT_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(PRODUCT_ID));
    }

    @Test
    void deleteProductTest() throws Exception {
      productService.deleteProduct(PRODUCT_CODE);
        mockMvc.perform(delete("/api/v1/product/" + PRODUCT_CODE))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}