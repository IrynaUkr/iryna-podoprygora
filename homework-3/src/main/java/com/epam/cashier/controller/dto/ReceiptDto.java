package com.epam.cashier.controller.dto;

import com.epam.cashier.controller.dto.group.OnCreate;
import com.epam.cashier.controller.service.model.OperationStatus;
import com.epam.cashier.controller.service.model.OperationType;
import com.epam.cashier.controller.service.model.ReceiptProducts;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptDto {
    private Integer receiptId;
    @NotBlank(message = "receipt number shouldn't be empty", groups = OnCreate.class)
    private String number;
    private Double sum;
    private Double amount;
    @FutureOrPresent
    private Date date;
    private Integer idUser;
    private OperationStatus status;
    private OperationType operationType;
    private List<ReceiptProducts> receiptProducts;
}
