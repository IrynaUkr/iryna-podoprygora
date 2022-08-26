package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.service.exception.ProductAlreadyExistException;
import com.epam.cashier.controller.service.mapper.ProductMapper;
import com.epam.cashier.controller.service.model.Product;
import com.epam.cashier.controller.service.repository.ProductRepository;
import com.epam.cashier.controller.service.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.cashier.controller.service.test.util.TestDataUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        ProductDto productDto = productService.getProductById(PRODUCT_ID);
        assertThat(testProductDto.getProductId(), equalTo(productDto.getProductId()));
        assertThat(testProductDto.getName(), equalTo(productDto.getName()));
        assertThat(testProductDto.getCode(), equalTo(productDto.getCode()));
    }

    @Test
    void listProduct() {
        List<Product> testList = new ArrayList<>();
        Product product = TestDataUtil.createProduct();
        testList.add(product);
        List<ProductDto> testProductDtos = ProductMapper.INSTANCE.mapToProductDtos(testList);

        when(productRepository.findAll()).thenReturn(testList);
        List<ProductDto> productDtos = productService.listProduct();
        assertThat(productDtos.size(), equalTo(testProductDtos.size()));
        for (int i = 0; i < productDtos.size(); i++) {
            assertThat(productDtos.get(i), equalTo(productDtos.get(i)));
        }
    }

    @Test
    void createProduct() {
        Product testProduct = TestDataUtil.createProduct();
        ProductDto testProductDto = ProductMapper.INSTANCE.mapToProductDto(testProduct);
        when(productRepository.existsByCode(PRODUCT_CODE)).thenReturn(false);
        when(productRepository.save(any())).thenReturn(testProduct);
        ProductDto productDto = productService.createProduct(testProductDto);
        assertThat(testProductDto.getDescription(), equalTo(productDto.getDescription()));
        assertThat(testProductDto.getName(), equalTo(productDto.getName()));
        assertThat(testProductDto.getAmount(), equalTo(productDto.getAmount()));
    }

    @Test
    void createProductAlreadyExist() {
        Product product = TestDataUtil.createProduct();
        ProductDto productDto = ProductMapper.INSTANCE.mapToProductDto(product);
        when(productRepository.existsByCode(PRODUCT_CODE)).thenReturn(true);
        assertThrows(ProductAlreadyExistException.class,
                () -> productService.createProduct(productDto));
    }

    @Test
    void updateProductTest() {
        Product testProduct = TestDataUtil.createProduct();
        ProductDto testProductDto = ProductMapper.INSTANCE.mapToProductDto(testProduct);
        when(productRepository.existsByCode(PRODUCT_CODE)).thenReturn(false);
        when(productRepository.save(any())).thenReturn(testProduct);
        ProductDto productDto = productService.createProduct(testProductDto);
        assertThat(testProductDto.getDescription(), equalTo(productDto.getDescription()));
        assertThat(testProductDto.getName(), equalTo(productDto.getName()));
        assertThat(testProductDto.getAmount(), equalTo(productDto.getAmount()));
    }

    @Test
    void mapPresentProductFieldsProductDtoFieldsTest() {
        ProductDto testProductDtoNew = createProductDto();
        Product testProductOld = TestDataUtil.createProduct();
        testProductOld.setAmount(654321.00);

        Product productNew = productService
                .mapPresentProductFieldsProductDtoFields(testProductOld, testProductDtoNew);
        System.out.println(productNew.getAmount());

        assertThat(productNew.getAmount(),equalTo(testProductOld.getAmount()));
    }

    @Test
    void deleteProduct() {
        Product testProduct = TestDataUtil.createProduct();
        when(productRepository.findByCode(PRODUCT_CODE)).thenReturn(Optional.of(testProduct));
        productService.deleteProduct(PRODUCT_CODE);

        verify(productRepository, times(1)).delete(testProduct);
    }
}