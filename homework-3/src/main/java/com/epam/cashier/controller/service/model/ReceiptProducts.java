package com.epam.cashier.controller.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReceiptProducts {
    private Integer productId;
    private double amount;
    private double price;
    private String name;
    private String code;
    private String uom;
}
