package com.epam.cashier.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private Integer productId;
    private String code;
    private String name;
    private String description;
    private Double price;
    private Double amount;
    private String uom;
}
