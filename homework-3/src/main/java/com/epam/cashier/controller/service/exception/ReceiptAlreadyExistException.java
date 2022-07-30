package com.epam.cashier.controller.service.exception;

import com.epam.cashier.controller.service.model.ErrorType;

public class ReceiptAlreadyExistException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Receipt is already exist";

    public ReceiptAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    public ReceiptAlreadyExistException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
