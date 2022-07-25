package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.UserService;
import com.epam.cashier.controller.service.exception.ReceiptNotFoundException;
import com.epam.cashier.controller.service.exception.RoleNotFoundException;
import com.epam.cashier.controller.service.exception.UserAlreadyExistException;
import com.epam.cashier.controller.service.exception.UserNotFoundException;
import com.epam.cashier.controller.service.mapper.UserMapper;
import com.epam.cashier.controller.service.model.Role;
import com.epam.cashier.controller.service.model.User;
import com.epam.cashier.controller.service.repository.RoleRepository;
import com.epam.cashier.controller.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDto getUser(String login) {
        log.info("Finding user by login");
        User user = userRepository.findByLogin(login)
                .orElseThrow(ReceiptNotFoundException::new);
        log.info("User with  login {} was found: " + login);
        return UserMapper.INSTANCE.mapToUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("Finding all users");
        List<User> users;
        Pageable paging = PageRequest.of(0, 3, Sort.by("login"));
        Page<User> allUsers = userRepository.findAll(paging);
        if (allUsers.hasContent()) {
            users = allUsers.getContent();
        } else {
            users = new ArrayList<>();
        }
        return UserMapper.INSTANCE.mapUserDtos(users);
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        log.info("creating User");
        Role role = userDto.getRole();
        String roleName = role.getRoleName();
        if (userRepository.existsByLogin(userDto.getLogin())) {
            throw new UserAlreadyExistException("User with {} login: is already exist" + userDto.getLogin());
        } else {
            userDto.setRole(
                    roleRepository
                            .findByRoleName(roleName.toLowerCase())
                            .orElseThrow(RoleNotFoundException::new)
            );

            User user = UserMapper.INSTANCE.mapToUser(userDto);
            user = userRepository.save(user);
            log.info("User was created");
            return UserMapper.INSTANCE.mapToUserDto(user);
        }
    }

    @Override
    @Transactional
    public UserDto updateUser(String login, UserDto userDto) {
        log.info("Started updating user by login");
        User persistedUser = userRepository.findByLogin(login)
                .orElseThrow(UserNotFoundException::new);
        User updatedUser = mapPresentUserFieldsUserDtoFields(persistedUser, userDto);
        userRepository.save(updatedUser);
        log.info("user was updated");
        return UserMapper.INSTANCE.mapToUserDto(updatedUser);
    }

    @Override
    public User mapPresentUserFieldsUserDtoFields(User user, UserDto userDto) {
        String email = userDto.getEmail();
        if (Objects.nonNull(email)) {
            user.setEmail(email);
        }
        String password = userDto.getPassword();
        if (Objects.nonNull(password)) {
            user.setPassword(password);
        }
        String surname = userDto.getSurname();
        if (Objects.nonNull(surname)) {
            user.setSurname(surname);
        }
        String address = userDto.getSurname();
        if (Objects.nonNull(address)) {
            user.setAddress(address);
        }
        String phoneNumber = userDto.getSurname();
        if (Objects.nonNull(phoneNumber)) {
            user.setPhoneNumber(phoneNumber);
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(String login) {
        log.info("deleteUser with login {}", login);
        User persistedUser = userRepository.findByLogin(login)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(persistedUser);
        log.info("user with login {} was deleted successfully" + login);
    }
}
