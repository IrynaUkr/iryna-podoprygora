package com.epam.cashier.controller.service;

import com.epam.cashier.controller.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUser(String email);

    List<UserDto> listUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String login, UserDto userDto);

    void deleteUser(String email);
}
