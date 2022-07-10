package com.epam.cashier.controller.service.exception;

import com.epam.cashier.controller.service.model.ErrorType;
public class UserNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "User not found";

    public UserNotFoundException(String message) {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}