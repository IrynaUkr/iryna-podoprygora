package com.epam.cashier.controller.service.repository;

import com.epam.cashier.controller.service.model.User;

import java.util.List;

public interface UserRepository {
    User getUser(String email);

    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(String email, User user);

    void deleteUser(String email);
}

