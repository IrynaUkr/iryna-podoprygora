package com.epam.cashier.controller.service.model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    private String nameProduct;
    private int idProduct;
    double amount;
    double price;
}
