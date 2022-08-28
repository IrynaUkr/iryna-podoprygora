package com.epam.cashier.controller.service;

import com.epam.cashier.controller.dto.ReceiptDto;
import com.epam.cashier.controller.dto.ReceiptProductDto;
import com.epam.cashier.controller.service.model.Receipt;

import java.util.List;

public interface ReceiptService {
    ReceiptDto getReceiptById(int id);

    List<ReceiptDto> listReceipt();

    ReceiptDto createReceipt(List<ReceiptProductDto> receiptProductsDto);

    ReceiptDto updateReceipt(String number, ReceiptDto receiptDto);

    Receipt mupPresentReceiptFieldsDtoFields(Receipt receipt, ReceiptDto receiptDto);

    void deleteReceiptByNumber(String number);
}
