package com.epam.cashier.controller.dto;

import com.epam.cashier.controller.dto.group.OnCreate;
import com.epam.cashier.controller.dto.group.OnUpdate;
import com.epam.cashier.controller.service.model.Role;
import com.epam.cashier.controller.service.validator.PhoneNumberConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.*;

@Data
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private int idUser;

    @NotBlank(message = "login shouldn't be empty", groups = OnCreate.class)
    private String login;
    @ToString.Exclude
    @Pattern(
            regexp = "(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).{8,32}$",
            message = "validation user-password")
    private String password;

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

    //@NotBlank(message = "role shouldn't be empty", groups = OnCreate.class)
    private Role role;
}
