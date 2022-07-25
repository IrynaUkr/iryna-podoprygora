package com.epam.cashier.controller.service.repository;

import com.epam.cashier.controller.service.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    Optional<User> findByLogin(String login);
    boolean deleteByLogin(String login);
    boolean existsByLogin(String login);
}
