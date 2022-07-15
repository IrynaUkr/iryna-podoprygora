package com.epam.cashier.controller.dto;

import com.epam.cashier.controller.dto.group.OnCreate;
import com.epam.cashier.controller.dto.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    @ReadOnlyProperty
    private int productId;

    @NotBlank(message = "code shouldn't be empty ", groups = OnCreate.class)
    private String code;

    @NotBlank(message = "name shouldn't be empty ", groups = OnCreate.class)
    private String name;

    private String description;

    @Positive(message = "price should be positive", groups = {OnCreate.class, OnUpdate.class})
    private Double price;

    @PositiveOrZero(message = "amount should be positive or zero", groups = {OnCreate.class, OnUpdate.class})
    private Double amount;
    private String uom;
}
