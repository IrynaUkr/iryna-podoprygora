package com.epam.cashier.controller.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    private Integer id;
    private Double value;
    private Date date;
    private String number;
    private Integer idUser;
    @Enumerated(EnumType.STRING)
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private OperationStatus status;

    private String description;

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", value=" + value +
                ", date=" + date +
                ", number='" + number + '\'' +
                ", idUser=" + idUser +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
