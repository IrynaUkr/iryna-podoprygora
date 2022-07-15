package com.epam.cashier.controller.service.repository;

import com.epam.cashier.controller.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean deleteByEmail(String email);
    boolean existsByLogin(String login);
}
