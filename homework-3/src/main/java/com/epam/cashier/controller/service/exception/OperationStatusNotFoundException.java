package com.epam.cashier.controller.service.exception;

import com.epam.cashier.controller.service.model.ErrorType;

public class OperationStatusNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Operation Status not found";

    public OperationStatusNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public OperationStatusNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
