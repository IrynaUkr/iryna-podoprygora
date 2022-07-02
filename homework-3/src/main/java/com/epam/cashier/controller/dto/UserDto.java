package com.epam.cashier.controller.dto;

import com.epam.cashier.controller.service.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String login;
    private String surname;
    private String phoneNumber;
    private String email;
    private String address;
    private Role role;
}
