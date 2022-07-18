package com.epam.cashier.controller.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Integer idUser;
    private String login;
    private String password;
    private String surname;
    private String phoneNumber;
    private String email;
    private String address;
    private Role role;
}
