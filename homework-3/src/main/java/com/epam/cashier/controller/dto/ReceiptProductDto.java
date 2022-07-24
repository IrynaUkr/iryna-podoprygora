package com.epam.cashier.controller.dto;

import com.epam.cashier.controller.service.model.Product;
import com.epam.cashier.controller.service.model.Receipt;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptProductDto {
    private int id;
    private Product product;
    private Receipt receipt;
    private double amount;
    private double price;
}