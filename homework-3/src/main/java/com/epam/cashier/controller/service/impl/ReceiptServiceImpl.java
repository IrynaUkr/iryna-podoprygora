package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.dto.ReceiptDto;
import com.epam.cashier.controller.dto.ReceiptProductDto;
import com.epam.cashier.controller.service.ReceiptService;
import com.epam.cashier.controller.service.exception.*;
import com.epam.cashier.controller.service.mapper.ReceiptMapper;
import com.epam.cashier.controller.service.mapper.ReceiptProductMapper;
import com.epam.cashier.controller.service.model.*;
import com.epam.cashier.controller.service.repository.OperationStatusRepository;
import com.epam.cashier.controller.service.repository.ProductRepository;
import com.epam.cashier.controller.service.repository.ReceiptProductRepository;
import com.epam.cashier.controller.service.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final OperationStatusRepository statusRepository;
    private final ReceiptProductRepository receiptProductRepository;
    private  final ProductRepository   productRepository;

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
    public ReceiptDto createReceipt(List<ReceiptProductDto> receiptProductsDto) {
        log.info("creating Receipt");
        Receipt receipt = new Receipt();
        OperationStatus status = statusRepository
                .findByStatusName("created")
                .orElseThrow(OperationStatusNotFoundException::new);
        receipt.setStatus(status);
        Receipt savedReceipt = receiptRepository.save(receipt);
        log.info("receipt number {} was created", savedReceipt.getNumber());
        log.info("add list of products with amount and price to receipt:");
        List<ReceiptProducts> receiptProducts = ReceiptProductMapper.INSTANCE.mapToReceiptProduct(receiptProductsDto);
        for (int i = 0; i < receiptProducts.size(); i++) {
            ReceiptProducts rp = receiptProducts.get(i);
            Integer productId = rp.getProduct().getProductId();
            Product product = productRepository.findByProductId(productId).orElseThrow(ProductNotFoundException::new);
            rp.setProduct(product);
            rp.setReceipt(savedReceipt);
            receiptProductRepository.save(rp);
        }
        ReceiptProductMapper.INSTANCE.mapToReceiptProductDtos(receiptProducts);
        log.info("list of products with amount and price was added");
        return ReceiptMapper.INSTANCE.mapToReceiptDto(savedReceipt);
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
    public void deleteReceipt(String number) {
        log.info("delete product by number {}", number);
        Receipt persistedReceipt = receiptRepository.findByNumber(number)
                .orElseThrow(ReceiptAlreadyExistException::new);
        receiptRepository.delete(persistedReceipt);
        log.info("receipt with number {} was deleted", number);
    }
}
