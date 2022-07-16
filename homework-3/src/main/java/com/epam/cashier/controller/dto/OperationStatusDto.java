package com.epam.cashier.controller.dto;

import lombok.Getter;

@Getter
public enum OperationStatusDto {
    CREATED,
    CLOSED,
    CANCELLED,
    FISCALISED
}
