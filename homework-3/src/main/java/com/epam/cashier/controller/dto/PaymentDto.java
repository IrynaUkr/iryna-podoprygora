package com.epam.cashier.controller.dto;

import com.epam.cashier.controller.dto.group.OnCreate;
import com.epam.cashier.controller.dto.group.OnUpdate;
import com.epam.cashier.controller.service.model.OperationStatus;
import com.epam.cashier.controller.service.model.OperationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {
    @ReadOnlyProperty
    private Integer id;
    @Positive(message = "sum of payment should be positive", groups = {OnCreate.class, OnUpdate.class})
    private Double value;
    @NotBlank(message = "choose or enter number")
    private String number;
    private Date date;
    private Integer idUser;
    private OperationStatus status;
    @NotBlank(message = "operation type should be chosen", groups = {OnCreate.class, OnUpdate.class})
    private OperationType type;
    private String description;
}
