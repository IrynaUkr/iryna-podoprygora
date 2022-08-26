package com.epam.cashier.controller.service.repository.impl;

import com.epam.cashier.controller.service.exception.ProductNotFoundException;
import com.epam.cashier.controller.service.model.Product;
import com.epam.cashier.controller.service.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> productList = new ArrayList<>();

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productList);
    }

    @Override
    public Product getProductById(int id) {
        return productList
                .stream()
                .filter(product -> product.getProductId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("product is not found!"));
    }

    @Override
    public Product create(Product product) {
        productList.add(product);
        return product;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        boolean isDeleted = productList.removeIf(p -> p.getProductId() == id);
        if (isDeleted) {
            productList.add(product);
        } else {
            throw new ProductNotFoundException("product not found");
        }
        return product;
    }

    @Override
    public void deleteProduct(int id) {
        productList.removeIf(product -> product.getProductId() == id);
    }

    @Override
    public void deleteProductByCode(String code) {
        productList.removeIf(product -> product.getCode().equals(code));
    }
}
