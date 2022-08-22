package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.service.mapper.ProductMapper;
import com.epam.cashier.controller.service.model.Product;
import com.epam.cashier.controller.service.repository.ProductRepository;
import com.epam.cashier.controller.service.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.epam.cashier.controller.service.test.util.TestDataUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void getProductTest() {
        Product testProduct = TestDataUtil.createProduct();
        ProductDto testProductDto = ProductMapper.INSTANCE.mapToProductDto(testProduct);
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(testProduct));
        ProductDto productDto = productService.getProduct(PRODUCT_ID);
        assertThat(testProductDto.getProductId(),equalTo(productDto.getProductId()));
        assertThat(testProductDto.getName(),equalTo(productDto.getName()));
        assertThat(testProductDto.getCode(),equalTo(productDto.getCode()));
    }

    @Test
    void listProduct() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void mapPresentProductFieldsProductDtoFields() {
    }

    @Test
    void deleteProduct() {
    }
}