package com.epam.cashier.controller.service.exception;

import com.epam.cashier.controller.service.model.ErrorType;

public class PaymentNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Payment not found";

    public PaymentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public PaymentNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
