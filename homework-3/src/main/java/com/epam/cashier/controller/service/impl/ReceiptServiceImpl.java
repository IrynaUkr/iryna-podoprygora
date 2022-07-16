package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.dto.ReceiptDto;
import com.epam.cashier.controller.service.ReceiptService;
import com.epam.cashier.controller.service.exception.OperationStatusNotFoundException;
import com.epam.cashier.controller.service.exception.ProductAlreadyExistException;
import com.epam.cashier.controller.service.exception.ReceiptAlreadyExistException;
import com.epam.cashier.controller.service.exception.UserNotFoundException;
import com.epam.cashier.controller.service.mapper.ReceiptMapper;
import com.epam.cashier.controller.service.model.OperationStatus;
import com.epam.cashier.controller.service.model.OperationType;
import com.epam.cashier.controller.service.model.Receipt;
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
    public ReceiptDto getReceipt(String number) {
        log.info("Finding Receipt by number ");
        Receipt receipt = receiptRepository.findByNumber(number)
                .orElseThrow(UserNotFoundException::new);
        log.info("receipt with number {} was found", number);
        return ReceiptMapper.INSTANCE.mapToReceiptDto(receipt);
    }

    @Override
    public List<ReceiptDto> listReceipt() {
        log.info("Finding all receipts");
        List<Receipt> allReceipts = receiptRepository.findAll();
        return ReceiptMapper.INSTANCE.mapToReceiptDtos(allReceipts);
    }

    @Override
    public ReceiptDto createReceipt(ReceiptDto receiptDto) {
        log.info("creating Receipt");
        OperationStatus status = receiptDto.getStatus();
        String statusName = status.getStatusName();
        if (receiptRepository.existsByNumber(receiptDto.getNumber())) {
            throw new ReceiptAlreadyExistException();
        } else {
            receiptDto.setStatus(
                    statusRepository
                            .findByStatusName(statusName.toLowerCase())
                            .orElseThrow(OperationStatusNotFoundException::new)
            );
            Receipt receipt = ReceiptMapper.INSTANCE.mapToReceipt(receiptDto);
            receipt = receiptRepository.save(receipt);
            log.info("receipt number {} was created", receipt.getNumber());
            return ReceiptMapper.INSTANCE.mapToReceiptDto(receipt);
        }
    }

    @Override
    public ReceiptDto updateReceipt(String number, ReceiptDto receiptDto) {
        return null;
    }

    @Override
    public Receipt mupPresentReceiptFieldsDtoFields(Receipt receipt, ProductDto productDto) {
        return null;
    }

    @Override
    @Transactional
    public void deleteProduct(String number) {
        log.info("delete product by number {}", number);
        Receipt persistedReceipt = receiptRepository.findByNumber(number)
                .orElseThrow(ReceiptAlreadyExistException::new);
        receiptRepository.delete(persistedReceipt);
        log.info("receipt with number {} was deleted", number);
    }
}
