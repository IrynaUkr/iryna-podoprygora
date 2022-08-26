package com.epam.cashier.controller;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.UserService;
import com.epam.cashier.controller.service.test.config.TestConfig;
import com.epam.cashier.controller.service.test.util.TestDataUtil;

import static com.epam.cashier.controller.service.test.util.TestDataUtil.LOGIN;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(TestConfig.class)
class UserControllerTest {
    //We use @MockBean to mock away the business logic,
    // since we don’t want to test integration between controller and business logic,
    // but between controller and the HTTP layer. @MockBean automatically
    // replaces the bean of the same type in the application context with a Mockito mock.
    @MockBean
    private UserService userService;

    //The request body is generated using the ObjectMapper provided by Spring Boot,
    // serializing an object to a JSON string.
    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUsers() throws Exception {
        List<UserDto> listUserDtoes = TestDataUtil.createListUserDtoes();
        when(userService.listUsers()).thenReturn(listUserDtoes);

        mockMvc.perform(get("/api/v1/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createUserTest() throws Exception {
        UserDto userDto = TestDataUtil.createUserCashierDto();
        when(userService.createUser(userDto)).thenReturn(userDto);
        mockMvc.perform(post("/api/v1/user")
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.login").value(LOGIN));
    }

    @Test
    void updateUser() throws Exception {
        UserDto userDto = TestDataUtil.createUserCashierDto();
        when(userService.updateUser(LOGIN, userDto)).thenReturn(userDto);

        mockMvc.perform(patch("/api/v1/user/" + LOGIN)
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.login").value(LOGIN));
    }

    @Test
    void getUserTest() throws Exception {
        UserDto userDto = TestDataUtil.createUserCashierDto();
        when(userService.getUser(userDto.getLogin())).thenReturn(userDto);

        mockMvc.perform(get("/api/v1/user/" + LOGIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.login").value(LOGIN));
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/api/v1/user/" + LOGIN))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}