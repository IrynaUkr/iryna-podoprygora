package com.epam.cashier.controller.service.impl;


import com.epam.cashier.controller.dto.PaymentDto;
import com.epam.cashier.controller.service.PaymentService;
import com.epam.cashier.controller.service.exception.OperationStatusNotFoundException;
import com.epam.cashier.controller.service.exception.PaymentAlreadyExistException;
import com.epam.cashier.controller.service.exception.PaymentNotFoundException;
import com.epam.cashier.controller.service.mapper.PaymentMapper;
import com.epam.cashier.controller.service.model.OperationStatus;
import com.epam.cashier.controller.service.model.Payment;
import com.epam.cashier.controller.service.repository.OperationStatusRepository;
import com.epam.cashier.controller.service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OperationStatusRepository operationStatusRepository;


    @Override
    public PaymentDto getPaymentByNumber(String number) {
        log.info("Get payment by number:" + number);
        Payment payment = paymentRepository
                .findByNumber(number)
                .orElseThrow(PaymentNotFoundException::new);
        log.info("Payment with number: " + number + " was found");
        return PaymentMapper.INSTANCE.mapToPaymentDto(payment);
    }

    @Override
    public List<PaymentDto> listPayment() {
        log.info("find list of payments");
        List<Payment> payments = paymentRepository.findAll();
        log.info("payments list was found");
        return PaymentMapper.INSTANCE.mapToListPaymentDtoes(payments);
    }

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        log.info("create product number: " + paymentDto.getNumber());
        OperationStatus status = paymentDto.getStatus();
        if (paymentRepository.existsByNumber(paymentDto.getNumber())) {
            throw new PaymentAlreadyExistException();
        } else {
            paymentDto.setStatus(
                    operationStatusRepository
                            .findByStatusName(status.getStatusName())
                            .orElseThrow(OperationStatusNotFoundException::new));
            Payment payment = PaymentMapper.INSTANCE.mapToPayment(paymentDto);
            Payment save = paymentRepository.save(payment);
            log.info("Payment was created");
            return PaymentMapper.INSTANCE.mapToPaymentDto(save);
        }
    }

    @Override
    public PaymentDto updatePayment(String number, PaymentDto paymentDto) {
        log.info("update payment with number{}", number);
        Payment persistedPayment = paymentRepository.findByNumber(number)
                .orElseThrow(PaymentNotFoundException::new);
        Payment updatedPayment = mapPresentToDtoeFields(persistedPayment, paymentDto);
        Payment save = paymentRepository.save(updatedPayment);
        return PaymentMapper.INSTANCE.mapToPaymentDto(save);
    }

    @Override
    public Payment mapPresentToDtoeFields(Payment payment, PaymentDto paymentDto) {
        String description = paymentDto.getDescription();
        if (Objects.nonNull(description)) {
            payment.setDescription(description);
        }
        Date date = paymentDto.getDate();
        if (Objects.nonNull(date)) {
            payment.setDate(date);
        }
        Double value = paymentDto.getValue();
        if (Objects.nonNull(value)) {
            payment.setValue(value);
        }
        OperationStatus status = paymentDto.getStatus();
        if (Objects.nonNull(status)) {
            payment.setStatus(status);
        }
        return payment;
    }

    @Override
    public void deletePaymentByNumber(String number) {
        log.info("delete payment by number {} ", number);
        Payment persistedPayment = paymentRepository
                .findByNumber(number)
                .orElseThrow(PaymentNotFoundException::new);
        paymentRepository.delete(persistedPayment);
        log.info("payment with number {} was deleted", number);
    }
}
