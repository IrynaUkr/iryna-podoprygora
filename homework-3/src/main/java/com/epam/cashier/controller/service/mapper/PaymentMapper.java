package com.epam.cashier.controller.service.mapper;

import com.epam.cashier.controller.dto.PaymentDto;
import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.service.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentDto mapToPaymentDto(Payment payment);

    Payment mapToPayment(PaymentDto paymentDto);

    List<PaymentDto> mapToListPaymentDtoes(List<Payment> payments);
}
