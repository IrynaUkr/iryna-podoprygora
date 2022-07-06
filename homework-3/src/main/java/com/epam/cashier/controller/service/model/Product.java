package com.epam.cashier.controller.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Integer productId;
    private String code;
    private String name;
    private String description;
    private Double price;
    private Double amount;
}
