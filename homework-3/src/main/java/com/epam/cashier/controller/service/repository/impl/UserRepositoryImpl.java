package com.epam.cashier.controller.service.repository.impl;

import com.epam.cashier.controller.service.model.User;
import com.epam.cashier.controller.service.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {


    private final List<User> userList = new ArrayList<>();

    @Override
    public User getUser(String email) {
        return userList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User is not found!"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userList);
    }

    @Override
    public User createUser(User user) {
        userList.add(user);
        return user;
    }

    @Override
    public User updateUser(String email, User user) {
        boolean isDeleted = userList.removeIf(u -> u.getEmail().equals(email));
        if (isDeleted) {
            userList.add(user);
        } else {
            throw new RuntimeException("User is not found!");
        }
        return user;
    }

    @Override
    public void deleteUser(String email) {
        userList.removeIf(user -> user.getEmail().
                equals(email));
    }
}
