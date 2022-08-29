package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.ReceiptDto;
import com.epam.cashier.controller.dto.ReceiptProductDto;
import com.epam.cashier.controller.service.ReceiptService;
import com.epam.cashier.controller.service.exception.OperationStatusNotFoundException;
import com.epam.cashier.controller.service.exception.ReceiptNotFoundException;
import com.epam.cashier.controller.service.mapper.ReceiptMapper;
import com.epam.cashier.controller.service.mapper.ReceiptProductMapper;
import com.epam.cashier.controller.service.model.OperationStatus;
import com.epam.cashier.controller.service.model.Receipt;
import com.epam.cashier.controller.service.model.ReceiptProducts;
import com.epam.cashier.controller.service.repository.OperationStatusRepository;
import com.epam.cashier.controller.service.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final OperationStatusRepository statusRepository;

    @Override
    public ReceiptDto getReceiptById(int id) {
        log.info("Finding Receipt by id ");
        Receipt receipt = receiptRepository.findByReceiptId(id)
                .orElseThrow(ReceiptNotFoundException::new);
        log.info("receipt with id {} was found", id);
        return ReceiptMapper.INSTANCE.mapToReceiptDto(receipt);
    }

    @Override
    public List<ReceiptDto> listReceipt() {
        log.info("Finding all receipts");
        List<Receipt> allReceipts = receiptRepository.findAll();
        return ReceiptMapper.INSTANCE.mapToReceiptDtos(allReceipts);
    }

    @Override
    @Transactional
    public ReceiptDto createReceipt(List<ReceiptProductDto> receiptProductsDto) {
        log.info("creating Receipt");

        List<ReceiptProducts> receiptProducts = ReceiptProductMapper.INSTANCE.mapToReceiptProduct(receiptProductsDto);
        Receipt receipt = new Receipt(receiptProducts);

        OperationStatus status = statusRepository
                .findByStatusName("created")
                .orElseThrow(OperationStatusNotFoundException::new);
        receipt.setStatus(status);

        Receipt savedReceipt = receiptRepository.save(receipt);

        ReceiptDto receiptDto = ReceiptMapper.INSTANCE.mapToReceiptDto(savedReceipt);

        log.info("receipt number {} was created", savedReceipt.getNumber());
        return receiptDto;
    }
    @Override
    public ReceiptDto updateReceipt(String number, ReceiptDto receiptDto) {
        log.info("updating receipt number: {}", number);
        Receipt persistedReceipt = receiptRepository
                .findByNumber(number)
                .orElseThrow(ReceiptNotFoundException::new);
        Receipt updatedReceipt = mupPresentReceiptFieldsDtoFields(persistedReceipt, receiptDto);
        return ReceiptMapper.INSTANCE.mapToReceiptDto(updatedReceipt);
    }

    @Override
    public Receipt mupPresentReceiptFieldsDtoFields(Receipt receipt, ReceiptDto receiptDto) {
        if (receiptDto.getStatus() != null) {
            receipt.setStatus(receiptDto.getStatus());
        }
        if (receiptDto.getNumber() != null) {
            receipt.setNumber(receiptDto.getNumber());
        }
        if (receiptDto.getDate() != null) {
            receipt.setDate(receiptDto.getDate());
        }
        if (receiptDto.getReceiptProductsList() != null) {
            receipt.setReceiptProducts(receiptDto.getReceiptProductsList());
        }
        return receipt;
    }

    @Override
    @Transactional
    public void deleteReceiptByNumber(String number) {
        log.info("delete product by number {}", number);
        Receipt persistedReceipt = receiptRepository.findByNumber(number)
                .orElseThrow(ReceiptNotFoundException::new);
        receiptRepository.delete(persistedReceipt);
        log.info("receipt with number {} was deleted", number);
    }
}
