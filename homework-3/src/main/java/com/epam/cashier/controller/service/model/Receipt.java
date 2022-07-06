package com.epam.cashier.controller.service.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;

@Data
@Builder
public class Receipt {
    private Integer id;
    private String number;
    private Double sum;
    private Double amount;
    private Date date;
    private Integer idUser;                     //author
    private OperationStatus status;
    private OperationType operationType;      //sale or refund of goods
    private ArrayList<ReceiptProducts> receiptProducts;
}
