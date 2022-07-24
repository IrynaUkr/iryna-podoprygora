package com.epam.cashier.controller.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Integer productId;
    @Column(unique = true)
    private String code;
    private String name;
    private String description;
    private Double price;
    private Double amount;
    @OneToMany(mappedBy = "product")
    List<ReceiptProducts> receiptProducts;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                '}';
    }
}
