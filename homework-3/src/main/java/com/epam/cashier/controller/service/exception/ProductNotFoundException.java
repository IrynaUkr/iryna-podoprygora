package com.epam.cashier.controller.service.exception;

import com.epam.cashier.controller.service.model.ErrorType;

public class ProductNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Product not found";

    public ProductNotFoundException(String message) {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
