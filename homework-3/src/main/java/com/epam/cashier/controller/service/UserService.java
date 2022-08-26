package com.epam.cashier.controller.service;

import com.epam.cashier.controller.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserByEmail(String email);

    List<UserDto> listUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUserByEmail(String email, UserDto userDto);

    void deleteUserByEmail(String email);
}
