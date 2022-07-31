package com.epam.cashier.controller.service.test.util;

import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.model.Role;
import com.epam.cashier.controller.service.model.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestDataUtil {
    public static final String LOGIN = "login";
    private static final String SURNAME = "surname";

    private static final Role cashier = new Role();

    public static User createUserCashier() {
        cashier.setRoleName("cashier");
        return User.builder()
                .login(LOGIN)
                .surname(SURNAME)
                .role(cashier)
                .build();
    }

    public static UserDto createUserCashierDto() {
        cashier.setRoleName("cashier");
        return UserDto.builder()
                .login(LOGIN)
                .surname(SURNAME)
                .role(cashier)
                .build();
    }
}
