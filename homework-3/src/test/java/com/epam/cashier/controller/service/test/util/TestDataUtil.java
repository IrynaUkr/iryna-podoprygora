package com.epam.cashier.controller.service.test.util;

import com.epam.cashier.controller.dto.PaymentDto;
import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.model.OperationStatus;
import com.epam.cashier.controller.service.model.Payment;
import com.epam.cashier.controller.service.model.Role;
import com.epam.cashier.controller.service.model.User;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class TestDataUtil {
    public static final String LOGIN = "login";
    private static final String SURNAME = "surname";

    private static final Role cashier = new Role();
    private static final OperationStatus created = new OperationStatus();
    public static final String PAYMENT_NUMBER = "ABC123-1";
    public static final String PAYMENT_DESCRIPTION = "payment for 123-1 bill";

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

    public static Payment createPayment() {
        created.setStatusName("created");
        return Payment.builder()
                .number(PAYMENT_NUMBER)
                .description(PAYMENT_DESCRIPTION)
                .value(100.00)
                .status(created)
                .build();
    }
    public static PaymentDto createPaymentDto() {
        created.setStatusName("created");
        return PaymentDto.builder()
                .number(PAYMENT_NUMBER)
                .description(PAYMENT_DESCRIPTION)
                .value(100.00)
                .status(created)
                .build();
    }
}
