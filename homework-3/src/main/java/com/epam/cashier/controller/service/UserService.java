package com.epam.cashier.controller.service;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.model.User;

import java.util.List;

public interface UserService {
    UserDto getUserByLogin(String login);

    List<UserDto> listUsers();

    List<UserDto> listUsersCashiers();

    UserDto createUser(UserDto userDto);

    UserDto updateUserByLogin(String login, UserDto userDto);

    void deleteUserByLogin(String email);

    User mapPresentUserFieldsUserDtoFields(User user, UserDto userDto);
}
