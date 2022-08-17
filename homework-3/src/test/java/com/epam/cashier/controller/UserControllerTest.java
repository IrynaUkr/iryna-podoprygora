package com.epam.cashier.controller;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.UserService;
import com.epam.cashier.controller.service.test.config.TestConfig;
import com.epam.cashier.controller.service.test.util.TestDataUtil;
import static com.epam.cashier.controller.service.test.util.TestDataUtil.LOGIN;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(TestConfig.class)
class UserControllerTest {

    @MockBean
    private UserService userService;
    //  @MockBean
    // private UserAssembler userAssembler;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUsers() {
    }

    @Test
    void getAllUsersCashiers() {
    }

    @Test
    void getAllUsersMerchandisers() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void getUserTest() throws Exception {
        UserDto userDto = TestDataUtil.createUserCashierDto();
        when(userService.getUser(userDto.getLogin())).thenReturn(userDto);

        mockMvc.perform(get("/api/v1/user/"+ LOGIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.login").value(LOGIN));
    }

    @Test
    void deleteUser() {
    }
}