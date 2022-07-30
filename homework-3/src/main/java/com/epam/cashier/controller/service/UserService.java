package com.epam.cashier.controller.service;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.model.User;

import java.util.List;

public interface UserService {
    UserDto getUser(String email);

    List<UserDto> listUsers();
    List<UserDto> listUsersCashiers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String login, UserDto userDto);

    void deleteUser(String email);

    User mapPresentUserFieldsUserDtoFields(User user, UserDto userDto) ;
}
