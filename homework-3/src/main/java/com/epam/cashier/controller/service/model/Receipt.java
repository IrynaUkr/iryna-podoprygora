package com.epam.cashier.controller.service.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue
    private Integer receiptId;
    @Column(unique = true)
    private String number;
    private Double sum;
    private Double amount;
    private Date date;
    private Integer idUser;
    @Enumerated(EnumType.STRING)
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private OperationStatus status;
    private OperationType operationType;
    @OneToMany(mappedBy = "receipt")
    List<ReceiptProducts> receiptProducts;
}
