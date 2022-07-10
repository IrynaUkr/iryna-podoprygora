package com.epam.cashier.controller.service;

import com.epam.cashier.controller.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getProduct(int id);

    List<ProductDto> listProduct();

    ProductDto createProduct(ProductDto productDTO);

    ProductDto updateProduct(int id, ProductDto productDTO);

    void deleteProduct(int id);
}
