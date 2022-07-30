package com.epam.cashier.controller.service.repository;

import com.epam.cashier.controller.service.model.OperationStatus;
import com.epam.cashier.controller.service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationStatusRepository extends JpaRepository<OperationStatus, Integer> {
    Optional<OperationStatus> findByStatusName(String statusName);
}
