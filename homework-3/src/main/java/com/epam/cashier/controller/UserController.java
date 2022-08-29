package com.epam.cashier.controller;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.listUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{email}")
    public UserDto updateUser(@PathVariable String email, @RequestBody UserDto userDto) {
        return userService.updateUserByEmail(email, userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{email}")
    public UserDto getUser(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
