package com.epam.cashier.controller.service.mapper;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto mapToProductDto(Product product);

    Product mapToProduct(ProductDto productDto);

    List<ProductDto> mapToProductDtos(List<Product> products);
}
