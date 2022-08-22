package com.epam.cashier.controller.service.test.util;

import com.epam.cashier.controller.dto.PaymentDto;
import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.model.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestDataUtil {
    public static final String LOGIN = "login";
    private static final String SURNAME = "surname";

    private static final Role cashier = new Role();
    private static final OperationStatus created = new OperationStatus();
    public static final String PAYMENT_NUMBER = "ABC123-1";
    public static final String PAYMENT_DESCRIPTION = "payment for 123-1 bill";
    public static final String PRODUCT_CODE = "QWE12";
    public static final String PRODUCT_NAME = "chocolate";
    public static final int PRODUCT_ID = 1;

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
    public static Product createProduct(){
       return Product.builder()
                .amount(30.00)
                .name(PRODUCT_NAME)
                .code(PRODUCT_CODE)
                .price(10.00)
                .productId(PRODUCT_ID)
                .build();
    }
}
