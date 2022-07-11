package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.service.ProductService;
import com.epam.cashier.controller.service.model.Product;
import com.epam.cashier.controller.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDto getProduct(int id) {
        log.info(" get Product ");
        Product product = productRepository.getProduct(id);
        return mapProductToProductDto(product);
    }

    @Override
    public List<ProductDto> listProduct() {
        log.info(" listProduct() ");
        return productRepository
                .getAllProducts()
                .stream()
                .map(this::mapProductToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(ProductDto productDTO) {
        log.info("create PRODUCT"+productDTO.getCode());
        Product pr =mapProductDtoToProduct(productDTO);
        productRepository.create(pr);
        return mapProductToProductDto(pr);
    }

    @Override
    public ProductDto updateProduct(int id, ProductDto productDTO) {
        log.info("updateproduct with id {}", id);
        Product product =mapProductDtoToProduct(productDTO);
        product=productRepository.updateProduct(id,product);
        return mapProductToProductDto(product);
    }

    @Override
    public void deleteProduct(int id) {
        log.info("deleteUser with email {}", id);
        productRepository.deleteProduct(id);
    }

    public ProductDto mapProductToProductDto(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .amount(product.getAmount())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .code(product.getCode())
                .build();
    }

    public Product mapProductDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .productId(productDto.getProductId())
                .amount(productDto.getAmount())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .code(productDto.getCode())
                .build();
    }
}
