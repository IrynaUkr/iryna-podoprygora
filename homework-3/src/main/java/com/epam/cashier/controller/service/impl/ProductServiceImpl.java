package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.service.ProductService;
import com.epam.cashier.controller.service.mapper.ProductMapper;
import com.epam.cashier.controller.service.model.Product;
import com.epam.cashier.controller.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDto getProductById(int id) {
        log.info(" get Product ");
        Product product = productRepository.getProductById(id);
        return ProductMapper.INSTANCE.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> listProduct() {
        log.info(" listProduct() ");
        List<Product> allProducts = productRepository.getAllProducts();
        return ProductMapper.INSTANCE.mapToProductDtos(allProducts);
    }

    @Override
    public ProductDto createProduct(ProductDto productDTO) {
        log.info("create PRODUCT" + productDTO.getCode());
        Product pr = ProductMapper.INSTANCE.mapToProduct(productDTO);
        productRepository.create(pr);
        return ProductMapper.INSTANCE.mapToProductDto(pr);
    }

    @Override
    public ProductDto updateProduct(int id, ProductDto productDTO) {
        log.info("update product with id {}", id);
        Product pr = ProductMapper.INSTANCE.mapToProduct(productDTO);
        return ProductMapper.INSTANCE.mapToProductDto(pr);
    }

    @Override
    public void deleteProduct(int id) {
        log.info("deleteUser with email {}", id);
        productRepository.deleteProduct(id);
    }
}
