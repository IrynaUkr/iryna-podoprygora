package com.epam.cashier.controller.service.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    @Column(unique = true)
    private String login;
    private String password;
    private String surname;
    private String phoneNumber;
    private String email;
    private String address;
    @Enumerated(EnumType.STRING)
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
}
