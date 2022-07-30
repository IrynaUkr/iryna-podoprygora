package com.epam.cashier.controller.service.exception;

import com.epam.cashier.controller.service.model.ErrorType;

public class RoleNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Role not found";

    public RoleNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public RoleNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
