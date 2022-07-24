package com.epam.cashier.controller.service.mapper;

import com.epam.cashier.controller.dto.ReceiptDto;
import com.epam.cashier.controller.dto.ReceiptProductDto;
import com.epam.cashier.controller.service.model.Receipt;
import com.epam.cashier.controller.service.model.ReceiptProducts;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReceiptProductMapper {
    ReceiptProductMapper INSTANCE = Mappers.getMapper(ReceiptProductMapper.class);

    ReceiptProductDto mapToReceiptProductDto(ReceiptProducts receiptProducts);

    ReceiptProducts mapToReceiptProducts(ReceiptProductDto receiptProductDtoDto);

    List<ReceiptProductDto> mapToReceiptProductDtos(List<ReceiptProducts> receiptProducts);

    List<ReceiptProducts> mapToReceiptProduct(List<ReceiptProductDto> receiptProductDtos);
}
