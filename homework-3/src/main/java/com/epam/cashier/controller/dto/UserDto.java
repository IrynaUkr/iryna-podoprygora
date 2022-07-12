package com.epam.cashier.controller.dto;

import com.epam.cashier.controller.dto.group.OnCreate;
import com.epam.cashier.controller.dto.group.OnUpdate;
import com.epam.cashier.controller.service.model.Role;
import com.epam.cashier.controller.service.validator.PhoneNumberConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @NotBlank(message = "login shouldn't be empty", groups = OnCreate.class)
    private String login;

    @NotBlank(message = "surname shouldn't be empty", groups = OnCreate.class)
    private String surname;

    @PhoneNumberConstraint(groups = OnCreate.class)
    @NotBlank(message = "phone Number shouldn't be empty", groups = OnCreate.class)
    private String phoneNumber;

    @Email(message = "email is not valid", groups = OnCreate.class)
    @NotBlank(message = "email shouldn't be empty", groups = OnCreate.class)
    @Null(message = " email shouldn't be absent in request", groups = OnUpdate.class)
    private String email;

    @NotBlank(message = "address shouldn't be empty", groups = OnCreate.class)
    private String address;

    private Role role;
}