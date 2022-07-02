package com.epam.cashier.controller.service.repository;

import com.epam.cashier.controller.service.model.Product;
import com.epam.cashier.controller.service.model.User;

import java.util.List;

public interface ProductRepository {
    public List<Product> getAllProducts();
    public Product getProduct(int id);
    public Product create (Product product);
    public Product updateProduct(int id, Product product);
    public void deleteProduct(int id);
    public void deleteProductByCode(String code);
}
