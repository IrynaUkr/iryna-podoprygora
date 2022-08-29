package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.UserService;
import com.epam.cashier.controller.service.model.User;
import com.epam.cashier.controller.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto getUserByEmail(String email) {
        log.info(" get Users ");
        User user = userRepository.getUserByEmail(email);
        return mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        return userRepository.getAllUsers()
                .stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser");
        User user = mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUserByEmail(String email, UserDto userDto) {
        log.info("updateUser with email {}", email);
        User user = mapUserDtoToUser(userDto);
        user = userRepository.updateUser(email, user);
        return mapUserToUserDto(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        log.info("deleteUser with email {}", email);
        userRepository.deleteUser(email);
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .role(user.getRole())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .login(userDto.getLogin())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .surname(userDto.getSurname())
                .phoneNumber(userDto.getPhoneNumber())
                .build();
    }
}
