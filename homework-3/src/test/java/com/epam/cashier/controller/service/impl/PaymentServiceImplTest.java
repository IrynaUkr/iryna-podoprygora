package com.epam.cashier.controller.service.impl;

import static com.epam.cashier.controller.service.test.util.TestDataUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.epam.cashier.controller.dto.PaymentDto;
import com.epam.cashier.controller.service.exception.PaymentAlreadyExistException;
import com.epam.cashier.controller.service.exception.PaymentNotFoundException;
import com.epam.cashier.controller.service.mapper.PaymentMapper;
import com.epam.cashier.controller.service.model.OperationStatus;
import com.epam.cashier.controller.service.model.Payment;
import com.epam.cashier.controller.service.repository.OperationStatusRepository;
import com.epam.cashier.controller.service.repository.PaymentRepository;
import com.epam.cashier.controller.service.test.util.TestDataUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OperationStatusRepository operationStatusRepository;

    @Test
    void getPaymentTest() {
        Payment payment = TestDataUtil.createPayment();
        when(paymentRepository.findByNumber(PAYMENT_NUMBER)).thenReturn(Optional.of(payment));

        PaymentDto paymentDto = paymentService.getPayment(PAYMENT_NUMBER);

        assertThat(payment.getDescription(), equalTo(paymentDto.getDescription()));
        assertThat(payment.getValue(), equalTo(paymentDto.getValue()));
    }

    @Test
    void getPaymentNotFoundExceptionTest() {
        when(paymentRepository.findByNumber(PAYMENT_NUMBER)).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> paymentService.getPayment(PAYMENT_NUMBER));
    }

    @Test
    void createPaymentTest() {
        Payment payment = createPayment();
        PaymentDto paymentDto = PaymentMapper.INSTANCE.mapToPaymentDto(payment);
        OperationStatus status = payment.getStatus();

        when(operationStatusRepository.findByStatusName("created")).thenReturn(Optional.of(status));
        when(paymentRepository.save(any())).thenReturn(payment);

        PaymentDto paymentDtoTest = paymentService.createPayment(paymentDto);

        assertThat(payment.getDescription(), equalTo(paymentDtoTest.getDescription()));
        assertThat(payment.getValue(), equalTo(paymentDtoTest.getValue()));
    }
    @Test
    void createPaymentAlreadyExistExceptionTest(){
        PaymentDto paymentDtoTest = createPaymentDto();
        when(paymentRepository.existsByNumber(PAYMENT_NUMBER)).thenReturn(true);
        assertThrows(PaymentAlreadyExistException.class,
                ()-> paymentService.createPayment(paymentDtoTest));
    }
    @Test
    void updatePaymentTest(){
        Payment testPayment = createPayment();
        PaymentDto testPaymentDto = createPaymentDto();

        when(paymentRepository.findByNumber(PAYMENT_NUMBER)).thenReturn(Optional.of(testPayment));
        when(paymentRepository.save(any())).thenReturn(testPayment);

        PaymentDto paymentDto = paymentService.updatePayment(PAYMENT_NUMBER, testPaymentDto);
        assertThat(testPaymentDto.getDescription(),equalTo(paymentDto.getDescription()));
        assertThat(testPaymentDto.getValue(),equalTo(paymentDto.getValue()));
        assertThat(testPaymentDto.getStatus(),equalTo(paymentDto.getStatus()));
    }

    @Test
    void updatePaymentNotFoundExceptionTest(){
        PaymentDto testPaymentDto = createPaymentDto();
        when(paymentRepository.findByNumber(PAYMENT_NUMBER)).thenReturn(Optional.empty());

       assertThrows(PaymentNotFoundException.class,
               ()-> paymentService.updatePayment(PAYMENT_NUMBER,testPaymentDto));

    }

    @Test
    void deletePaymentTest(){
        Payment testPayment = createPayment();
        when(paymentRepository.findByNumber(PAYMENT_NUMBER)).thenReturn(Optional.of(testPayment));

        paymentService.deletePayment(PAYMENT_NUMBER);

        verify(paymentRepository,times(1)).delete(testPayment);
    }

    @Test
    void deletePaymentNotFoundExceptionTest(){
        when(paymentRepository.findByNumber(PAYMENT_NUMBER)).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class,
                ()-> paymentService.deletePayment(PAYMENT_NUMBER));
    }

    @Test
    void listPaymentTest(){
        paymentService.listPayment();
        verify(paymentRepository, times(1)).findAll();
    }


}