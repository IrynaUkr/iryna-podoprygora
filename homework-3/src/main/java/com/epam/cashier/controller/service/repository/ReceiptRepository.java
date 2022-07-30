package com.epam.cashier.controller.service.repository;

import com.epam.cashier.controller.service.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    Optional<Receipt> findByNumber(String number);

    Optional<Receipt> findByReceiptId(int id);

    boolean existsByNumber(String number);
}
