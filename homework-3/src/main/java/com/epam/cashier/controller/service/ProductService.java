package com.epam.cashier.controller.service;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.model.Product;

import java.util.List;

public interface ProductService {
    ProductDto getProduct(int id);

  List<ProductDto> listProduct();

    ProductDto createProduct(ProductDto productDTO);

    ProductDto updateProduct(String code, ProductDto productDTO);

    Product mapPresentProductFieldsProductDtoFields(Product product, ProductDto productDto);

    void deleteProduct(String code);
}
