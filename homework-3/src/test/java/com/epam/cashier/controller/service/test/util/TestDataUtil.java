package com.epam.cashier.controller.service.test.util;

import com.epam.cashier.controller.dto.*;
import com.epam.cashier.controller.service.mapper.PaymentMapper;
import com.epam.cashier.controller.service.mapper.ProductMapper;
import com.epam.cashier.controller.service.mapper.ReceiptMapper;
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
    public static final String PAYMENT_NUMBER = "ABC123-1";
    public static final String PAYMENT_DESCRIPTION = "payment for 123-1 bill";
    public static final String PRODUCT_CODE = "QWE12";
    public static final String PRODUCT_NAME = "chocolate";
    public static final String RECEIPT_NUMBER = "receipt1";
    public static final int PRODUCT_ID = 1;
    public static final int RECEIPT_ID = 1;


    public static User createUserCashier() {
        cashier.setRoleName("cashier");
        return User.builder()
                .login(LOGIN)
                .surname(SURNAME)
                .role(cashier)
                .email("coffee@gmail.com")
                .phoneNumber("0984607617")
                .idUser(1)
                .build();
    }

    public static UserDto createUserCashierDto() {
        User userCashier = createUserCashier();
        return UserMapper.INSTANCE.mapToUserDto(userCashier);
    }

    public static List<User> createListUsers() {
        List<User> users = new ArrayList<>();
        User userCashier1 = createUserCashier();
        User userCashier2 = createUserCashier();
        userCashier2.setIdUser(2);
        users.add(userCashier1);
        users.add(userCashier2);
        return users;
    }

    public static List<UserDto> createListUserDtoes() {
        List<User> listUsers = createListUsers();
        return UserMapper.INSTANCE.mapUserDtos(listUsers);
    }

    public static Payment createPayment() {
        OperationStatus created = new OperationStatus();
        created.setStatusName("created");
        return Payment.builder()
                .number(PAYMENT_NUMBER)
                .description(PAYMENT_DESCRIPTION)
                .value(100.00)
                .status(created)
                .build();
    }

    public static PaymentDto createPaymentDto() {
        Payment payment = createPayment();
        return PaymentMapper.INSTANCE.mapToPaymentDto(payment);
    }

    public static List<Payment> createPaymentList() {
        Payment payment1 = createPayment();
        payment1.setValue(10.00);
        Payment payment2 = createPayment();
        payment2.setValue(100.00);
        List<Payment> payments = new ArrayList<>();
        payments.add(payment1);
        payments.add(payment2);
        return payments;
    }

    public static List<PaymentDto> createPaymentDtosList() {
        List<Payment> paymentList = createPaymentList();
        return PaymentMapper.INSTANCE.mapToListPaymentDtoes(paymentList);
    }

    public static Product createProduct() {
        return Product.builder()
                .amount(30.00)
                .name(PRODUCT_NAME)
                .code(PRODUCT_CODE)
                .price(10.00)
                .productId(PRODUCT_ID)
                .build();
    }

    public static ProductDto createProductDto() {
        return ProductDto.builder()
                .amount(30.00)
                .name(PRODUCT_NAME)
                .code(PRODUCT_CODE)
                .price(10.00)
                .productId(PRODUCT_ID)
                .build();
    }

    public static List<ProductDto> createListProductsDtoes() {
        Product product = TestDataUtil.createProduct();
        List<Product> products = new ArrayList<>();
        products.add(product);
        return ProductMapper.INSTANCE.mapToProductDtos(products);
    }

    public static Receipt createReceipt() {
        OperationStatus created = new OperationStatus();
        created.setStatusName("created");
        return Receipt.builder()
                .number(RECEIPT_NUMBER)
                .status(created)
                .operationType(OperationType.SALE)
                .build();
    }

    public static ReceiptDto createReceiptDto() {
        Receipt receipt = createReceipt();
        return ReceiptMapper.INSTANCE.mapToReceiptDto(receipt);
    }

    public static List<Receipt> createListReceipt() {
        Receipt r1 = createReceipt();
        Receipt r2 = createReceipt();
        r2.setNumber("22a");
        List<Receipt> dtoList = new ArrayList<>();
        dtoList.add(r1);
        dtoList.add(r2);
        return dtoList;
    }
    public static ReceiptProductDto createReceiptProductDto(){
        return ReceiptProductDto.builder()
                .id(1)
                .amount(100.00)
                .product(createProduct())
                .price(20.00)
                .build();
    }
    public static List<ReceiptProductDto> createListReceiptProductDto(){
        ReceiptProductDto r1 = createReceiptProductDto();
        List<ReceiptProductDto> rp = new ArrayList<>();
        rp.add(r1);
        return rp;
    }
}
