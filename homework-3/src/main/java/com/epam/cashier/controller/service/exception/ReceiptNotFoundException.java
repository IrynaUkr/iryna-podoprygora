package com.epam.cashier.controller.service.exception;

import com.epam.cashier.controller.service.model.ErrorType;

public class ReceiptNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Receipt not found";

    public ReceiptNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ReceiptNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
