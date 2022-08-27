package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.UserService;
import com.epam.cashier.controller.service.mapper.UserMapper;
import com.epam.cashier.controller.service.model.User;
import com.epam.cashier.controller.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto getUserByEmail(String email) {
        log.info(" get Users ");
        User user = userRepository.getUserByEmail(email);
        return UserMapper.INSTANCE.mapToUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        List<User> allUsers = userRepository.getAllUsers();
        return UserMapper.INSTANCE.mapUserDtos(allUsers);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser");
        User user = UserMapper.INSTANCE.mapToUser(userDto);
        user = userRepository.createUser(user);
        return UserMapper.INSTANCE.mapToUserDto(user);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("updateUser with email {}", email);
        User user = UserMapper.INSTANCE.mapToUser(userDto);
        user = userRepository.updateUser(email, user);
        return UserMapper.INSTANCE.mapToUserDto(user);
    }

    @Override
    public void deleteUser(String email) {
        log.info("deleteUser with email {}", email);
        userRepository.deleteUser(email);
    }
}
