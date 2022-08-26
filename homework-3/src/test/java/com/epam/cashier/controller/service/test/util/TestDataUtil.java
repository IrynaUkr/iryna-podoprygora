package com.epam.cashier.controller.service.test.util;

import com.epam.cashier.controller.dto.PaymentDto;
import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.dto.UserDto;
import com.epam.cashier.controller.service.mapper.ProductMapper;
import com.epam.cashier.controller.service.mapper.UserMapper;
import com.epam.cashier.controller.service.model.*;
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
    public static final String PRODUCT_CODE = "QWE12";
    public static final String PRODUCT_NAME = "chocolate";
    public static final int PRODUCT_ID = 1;

    public static User createUserCashier() {
        cashier.setRoleName("cashier");
        return User.builder()
                .login(LOGIN)
                .surname(SURNAME)
                .role(cashier)
                .email("coffee@gmail.com")
                .address("Kyiv")
                .phoneNumber("0984607617")
                .idUser(1)
                .build();
    }

    public static UserDto createUserCashierDto() {
        User userCashier = createUserCashier();
        return UserMapper.INSTANCE.mapToUserDto(userCashier);
    }
    public static List<User> createListUsers(){
        List<User> users = new ArrayList<>();
        User userCashier1 = createUserCashier();
        User userCashier2 = createUserCashier();
        userCashier2.setIdUser(2);
        users.add(userCashier1);
        users.add(userCashier2);
        return users;
    }
     public static List<UserDto> createListUserDtoes(){
         List<User> listUsers = createListUsers();
         return UserMapper.INSTANCE.mapUserDtos(listUsers);
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
    public static ProductDto createProductDto(){
        return ProductDto.builder()
                .amount(30.00)
                .name(PRODUCT_NAME)
                .code(PRODUCT_CODE)
                .price(10.00)
                .productId(PRODUCT_ID)
                .build();
    }
    public static List<ProductDto>  createListProductsDtoes() {
        Product product = TestDataUtil.createProduct();
        List<Product> products= new ArrayList<>();
        products.add(product);
        return ProductMapper.INSTANCE.mapToProductDtos(products);
    }
}
