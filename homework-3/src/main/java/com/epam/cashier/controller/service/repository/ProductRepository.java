package com.epam.cashier.controller.service.repository;

import com.epam.cashier.controller.service.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();

    Product getProductById(int id);

    Product create(Product product);

    Product updateProduct(int id, Product product);

    void deleteProduct(int id);

    void deleteProductByCode(String code);
}
