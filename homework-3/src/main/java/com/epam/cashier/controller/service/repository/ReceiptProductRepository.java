package com.epam.cashier.controller.service.repository;

import com.epam.cashier.controller.service.model.Receipt;
import com.epam.cashier.controller.service.model.ReceiptProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReceiptProductRepository extends JpaRepository<ReceiptProducts, Integer> {
    List<ReceiptProducts> findAllByReceipt(Receipt receipt);
}
