package com.epam.cashier.controller.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {
    @Override
    public void initialize(PhoneNumberConstraint phoneNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        System.out.println(contactField + "my field");
        return contactField != null && contactField.matches("\\d+")
                && (contactField.length() > 8) && (contactField.length() < 14);
    }
}
