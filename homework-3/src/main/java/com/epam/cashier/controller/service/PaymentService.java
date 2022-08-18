package com.epam.cashier.controller.service;

import com.epam.cashier.controller.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    PaymentDto getPayment(int id);

    List<PaymentDto> listPayment();

    PaymentDto createPayment(PaymentDto paymentDto);

    PaymentDto updatePayment(String number, PaymentDto paymentDto);

    void deletePayment(String number);

}
