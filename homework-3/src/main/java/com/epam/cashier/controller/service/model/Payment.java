package com.epam.cashier.controller.service.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class Payment {
    private Integer id;
    private Double value;
    private Date date;
    private String number;
    private Integer idUser;
    private OperationStatus status;
    private OperationType type;
    private String description;
}
