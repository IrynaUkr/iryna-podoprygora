package com.epam.cashier.controller.service.repository;

import com.epam.cashier.controller.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByCode(String code);

    Optional<Product> findByProductId(int id);

    boolean existsByCode(String code);
}
