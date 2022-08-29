package com.epam.cashier.controller;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.dto.group.OnCreate;
import com.epam.cashier.controller.dto.group.OnUpdate;
import com.epam.cashier.controller.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Api(tags = "API description for SWAGGER documentation User")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class UserController {
    private final UserService userService;

    @ApiOperation("get all users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.listUsers();
    }
    @ApiOperation("get all usersCashiers")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cashier")
    public List<UserDto> getAllUsersCashiers() {
        return userService.listUsersCashiers();
    }

    @ApiOperation("create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public UserDto createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
        return userService.createUser(userDto);
    }

    @ApiOperation("update user by login")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping (value = "/{login}")
    public UserDto updateUser(@PathVariable @Validated(OnUpdate.class) String login, @RequestBody UserDto userDto) {
        return userService.updateUserByLogin(login, userDto);
    }

    @ApiOperation("get user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{login}")
    public UserDto getUser(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @ApiOperation("delete user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        userService.deleteUserByLogin(login);
        return ResponseEntity.noContent().build();
    }
}
