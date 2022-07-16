package com.epam.cashier.controller.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationStatus {
    @Id
    @GeneratedValue
    private int id;
    String statusName;

    @JsonIgnore
    @OneToMany(mappedBy = "operationStatus")
    private Set<Receipt> receipts;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        OperationStatus status = (OperationStatus) o;
        return Objects.equals(id, status.id);
    }
}
