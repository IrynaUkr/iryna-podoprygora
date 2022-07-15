package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.UserService;
import com.epam.cashier.controller.service.exception.UserAlreadyExistException;
import com.epam.cashier.controller.service.exception.UserNotFoundException;
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
    public UserDto getUser(String email) {
        log.info("Finding user by email");
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        log.info("User with {} email was found: " + email);
        return UserMapper.INSTANCE.mapToUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("Finding all users");
        List<User> allUsers = userRepository.findAll();
        return UserMapper.INSTANCE.mapUserDtos(allUsers);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser");
        if (userRepository.existsByLogin(userDto.getLogin())) {
            throw new UserAlreadyExistException("User with {} login: is already exist" + userDto.getLogin());
        } else {
            int idUserDTO = userDto.getIdUser();
            System.out.println(idUserDTO + "userDTO ID");
            User user = UserMapper.INSTANCE.mapToUser(userDto);
            user = userRepository.save(user);
            int idUser = user.getIdUser();
            System.out.println(idUser + "user ID");
            log.info("User was created");
            return UserMapper.INSTANCE.mapToUserDto(user);
        }
    }

    @Override
    public UserDto updateUser(int id, UserDto userDto) {
        log.info("Started updating user by id");

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " is not found");
        }
        User user = UserMapper.INSTANCE.mapToUser(userDto);
        user.setIdUser(id);
        user = userRepository.save(user);
        return UserMapper.INSTANCE.mapToUserDto(user);
    }

    @Override
    public void deleteUser(String email) {
        log.info("deleteUser with email {}", email);
        userRepository.deleteByEmail(email);
    }
}
