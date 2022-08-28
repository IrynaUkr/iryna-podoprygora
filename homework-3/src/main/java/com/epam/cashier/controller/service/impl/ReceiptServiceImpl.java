package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.ReceiptDto;
import com.epam.cashier.controller.dto.ReceiptProductDto;
import com.epam.cashier.controller.service.ReceiptService;
import com.epam.cashier.controller.service.exception.*;
import com.epam.cashier.controller.service.mapper.ReceiptMapper;
import com.epam.cashier.controller.service.model.*;
import com.epam.cashier.controller.service.repository.OperationStatusRepository;
import com.epam.cashier.controller.service.repository.ProductRepository;
import com.epam.cashier.controller.service.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final OperationStatusRepository statusRepository;
    private final ProductRepository productRepository;

    @Override
    public ReceiptDto getReceiptById(int id) {
        log.info("Finding Receipt by id ");
        Receipt receipt = receiptRepository.findByReceiptId(id)
                .orElseThrow(ReceiptNotFoundException::new);
        log.info("receipt with id {} was found", id);
        ReceiptDto receiptDto = new ReceiptDto();
        receiptDto.setReceiptId(id);
        receiptDto.setStatus(receipt.getStatus());
        return receiptDto;
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

        List<ReceiptProducts> receiptProducts = mapReceiptProductList(receiptProductsDto);
        Receipt receipt = new Receipt(receiptProducts);

        OperationStatus status = statusRepository
                .findByStatusName("created")
                .orElseThrow(OperationStatusNotFoundException::new);
        receipt.setStatus(status);

        Receipt savedReceipt = receiptRepository.save(receipt);

        ReceiptDto receiptDto = addProductDtoOrderList(receiptProductsDto);
        receiptDto.setReceiptId(savedReceipt.getReceiptId());
        receiptDto.setStatus(status);
        log.info("receipt number {} was created", savedReceipt.getNumber());
        return receiptDto;
    }

    public List<ReceiptProducts> mapReceiptProductList(List<ReceiptProductDto> receiptProductsDto){
        List<ReceiptProducts> result = new ArrayList<>();
        for(ReceiptProductDto r: receiptProductsDto){
            ReceiptProducts rp =  new ReceiptProducts();
            rp.setProduct(r.getProduct());
            rp.setAmount(r.getAmount());
            rp.setPrice(r.getPrice());
            result.add(rp);
        }
        return result;
    }
    public ReceiptDto addProductDtoOrderList(List<ReceiptProductDto> receiptProductDtos) {
        log.info("add list of products with amount and price to receipt:");
        ReceiptDto receiptDto = new ReceiptDto();
        List<ProductOrder> productOrders = new ArrayList<>();
        for (ReceiptProductDto rp : receiptProductDtos) {
            Integer productId = rp.getProduct().getProductId();
            Product product = productRepository.findByProductId(productId)
                    .orElseThrow(ProductNotFoundException::new);
            ProductOrder po = new ProductOrder();
            po.setIdProduct(productId);
            po.setAmount(rp.getAmount());
            po.setPrice(rp.getPrice());
            po.setNameProduct(product.getName());
            productOrders.add(po);
        }
        receiptDto.setProductOrders(productOrders);
        log.info("list products was added to receiptDto");
        return receiptDto;
    }

    @Override
    public ReceiptDto updateReceipt(String number, ReceiptDto receiptDto) {

        return null;
    }

    @Override
    public Receipt mupPresentReceiptFieldsDtoFields(Receipt receipt, ReceiptDto receiptDto) {
        if(receiptDto.getStatus()!=null){
            receipt.setStatus(receiptDto.getStatus());
        }
        if(receiptDto.getNumber()!=null){
            receipt.setNumber(receiptDto.getNumber());
        }
        if(receiptDto.getDate()!=null){
            receipt.setDate(receiptDto.getDate());
        }



        return receipt;
    }

    @Override
    @Transactional
    public void deleteReceiptByNumber(String number) {
        log.info("delete product by number {}", number);
        Receipt persistedReceipt = receiptRepository.findByNumber(number)
                .orElseThrow(ReceiptAlreadyExistException::new);
        receiptRepository.delete(persistedReceipt);
        log.info("receipt with number {} was deleted", number);
    }
}
