package com.epam.cashier.controller.dto;

import com.epam.cashier.controller.service.model.OperationStatus;
import com.epam.cashier.controller.service.model.ReceiptProducts;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import java.sql.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptDto {
    private Integer receiptId;
    private String number;
    @FutureOrPresent
    private Date date;
    private Integer idUser;
    private OperationStatus status;
    private List<ReceiptProducts> receiptProductsList;


    @Override
    public String toString() {
        return "ReceiptDto{" +
                "receiptId=" + receiptId +
                '}';
    }
}
