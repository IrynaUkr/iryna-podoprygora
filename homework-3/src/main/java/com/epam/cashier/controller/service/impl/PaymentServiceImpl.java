package com.epam.cashier.controller.service.impl;


import com.epam.cashier.controller.dto.PaymentDto;
import com.epam.cashier.controller.service.PaymentService;
import com.epam.cashier.controller.service.exception.PaymentNotFoundException;
import com.epam.cashier.controller.service.exception.ProductAlreadyExistException;
import com.epam.cashier.controller.service.mapper.PaymentMapper;
import com.epam.cashier.controller.service.mapper.ProductMapper;
import com.epam.cashier.controller.service.model.Payment;
import com.epam.cashier.controller.service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;


    @Override
    public PaymentDto getPayment(int id) {
        log.info("Get payment by id:" + id);
        Payment payment = paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);
        log.info("Payment with id: " + id +" was found");
        return PaymentMapper.INSTANCE.mapToPaymentDto(payment);
    }

    @Override
    public List<PaymentDto> listPayment(){
        log.info("find list of payments");
        List<Payment> payments = paymentRepository.findAll();
        log.info("payments list was found");
        return PaymentMapper.INSTANCE.mapToListPaymentDtoes(payments);
    }

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        log.info("create product number: "+ paymentDto.getNumber());
        if (paymentRepository.existsByNumber(paymentDto.getNumber())){
            throw new ProductAlreadyExistException();
        }else{
            Payment payment = PaymentMapper.INSTANCE.mapToPayment(paymentDto);
            Payment save = paymentRepository.save(payment);
            log.info("Payment was created");
            return PaymentMapper.INSTANCE.mapToPaymentDto(save);
        }

    }

    @Override
    public PaymentDto updatePayment(String number, PaymentDto paymentDto) {
        return null;
    }

    @Override
    public void deletePayment(String number) {

    }
}
