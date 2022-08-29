package com.epam.cashier.controller.service.mapper;

import com.epam.cashier.controller.dto.ReceiptDto;
import com.epam.cashier.controller.service.model.Receipt;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReceiptMapper {
    ReceiptMapper INSTANCE = Mappers.getMapper(ReceiptMapper.class);

    ReceiptDto mapToReceiptDto(Receipt receipt);

    List<ReceiptDto> mapToReceiptDtos(List<Receipt> receipts);

}
