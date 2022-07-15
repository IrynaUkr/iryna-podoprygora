package com.epam.cashier.controller.service.exception;

import com.epam.cashier.controller.service.model.ErrorType;

public class UserAlreadyExistException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "User is already exist";

    public UserAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
