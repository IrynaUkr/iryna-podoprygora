package com.epam.cashier.controller.service.repository;

import com.epam.cashier.controller.service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    boolean existsByNumber(String number);
}
