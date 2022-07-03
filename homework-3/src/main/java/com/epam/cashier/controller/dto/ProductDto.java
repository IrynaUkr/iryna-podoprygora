package com.epam.cashier.controller.dto;

import com.epam.cashier.controller.dto.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Integer productId;

    @NotBlank(message = "code shouldn't be empty ", groups = OnCreate.class)
    private String code;

    @NotBlank(message = "name shouldn't be empty ", groups = OnCreate.class)
    private String name;

    private String description;

  //  @NotBlank(message = "name shouldn't be empty ", groups = OnCreate.class)
    private Double price;

  //  @NotBlank(message = "name shouldn't be empty ", groups = OnCreate.class)
    private Double amount;
    private String uom; //units of measure
}
