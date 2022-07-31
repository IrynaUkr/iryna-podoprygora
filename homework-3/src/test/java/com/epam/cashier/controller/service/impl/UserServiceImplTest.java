package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.exception.UserAlreadyExistException;
import com.epam.cashier.controller.service.exception.UserNotFoundException;
import com.epam.cashier.controller.service.model.Role;
import com.epam.cashier.controller.service.model.User;
import com.epam.cashier.controller.service.repository.RoleRepository;
import com.epam.cashier.controller.service.repository.UserRepository;
import com.epam.cashier.controller.service.test.util.TestDataUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.epam.cashier.controller.service.test.util.TestDataUtil.LOGIN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Test
    void getUserTest() {
        User user = TestDataUtil.createUserCashier();
        when(userRepository.findByLogin(LOGIN)).thenReturn(Optional.of(user));
        UserDto userDto = userService.getUser(LOGIN);

        assertThat(userDto, Matchers.allOf(
                hasProperty("login", equalTo(user.getLogin())),
                hasProperty("surname", equalTo(user.getSurname()))));
    }

    @Test
    void getUserUserNotFoundTest() {
        when(userRepository.findByLogin(LOGIN)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUser(LOGIN));
    }

    @Test
    void createUserTest() {
        User testUser = TestDataUtil.createUserCashier();
        UserDto testUserDto = TestDataUtil.createUserCashierDto();
        Role role = testUserDto.getRole();
        String roleName = role.getRoleName();
        when(roleRepository.findByRoleName(roleName)).thenReturn(Optional.of(role));
        when(userRepository.save(any())).thenReturn(testUser);

        UserDto userDto = userService.createUser(testUserDto);

        assertThat(userDto, allOf(
                hasProperty("login", equalTo(testUserDto.getLogin())),
                hasProperty("surname", equalTo(testUserDto.getSurname()))
        ));
    }

    @Test
    void createUserUserAlreadyExistExceptionTest() {
        UserDto testUserDto = TestDataUtil.createUserCashierDto();
        when(userRepository.existsByLogin(LOGIN)).thenReturn(true);
        assertThrows(UserAlreadyExistException.class, () -> userService.createUser(testUserDto));
    }

    @Test
    public void updateUserTest() {
        User testUser = TestDataUtil.createUserCashier();
        UserDto testUserDto = TestDataUtil.createUserCashierDto();
        when(userRepository.findByLogin(LOGIN)).thenReturn(Optional.of(testUser));
        when(userRepository.save(any())).thenReturn(testUser);

        userService.updateUser(testUser.getLogin(), testUserDto);

        assertThat(testUserDto, allOf(
                hasProperty("login", equalTo(testUserDto.getLogin())),
                hasProperty("surname", equalTo(testUserDto.getSurname()))
        ));
    }

    @Test
    public void updateUserUserNotFoundExceptionTest() {
        UserDto userDto = TestDataUtil.createUserCashierDto();
        when(userRepository.findByLogin(LOGIN)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(LOGIN, userDto));
    }

    @Test
    public void deleteUserTest() {
        User testUser = TestDataUtil.createUserCashier();
        when(userRepository.findByLogin(LOGIN)).thenReturn(Optional.of(testUser));

        userService.deleteUser(testUser.getLogin());
        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    public void deleteUserUserNotFoundExceptionTest() {
        User testUser = TestDataUtil.createUserCashier();
        when(userRepository.findByLogin(testUser.getLogin())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(testUser.getLogin()));
    }
}