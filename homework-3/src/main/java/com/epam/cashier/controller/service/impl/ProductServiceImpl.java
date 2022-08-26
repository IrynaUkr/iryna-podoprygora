package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.service.ProductService;
import com.epam.cashier.controller.service.exception.ProductAlreadyExistException;
import com.epam.cashier.controller.service.exception.ProductNotFoundException;
import com.epam.cashier.controller.service.mapper.ProductMapper;
import com.epam.cashier.controller.service.model.Product;
import com.epam.cashier.controller.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDto getProductById(int id) {
        log.info("Get Product by id: " + id);
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        log.info("Product with" + id + " was found");
        return ProductMapper.INSTANCE.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> listProduct() {
        log.info("Find list of Product");
        List <Product> products = productRepository.findAll();
        log.info("Products list was found");
        return ProductMapper.INSTANCE.mapToProductDtos(products);
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDTO) {
        log.info("create product with code" + productDTO.getCode());
        if (productRepository.existsByCode(productDTO.getCode())) {
            throw new ProductAlreadyExistException();
        } else {
            Product product = ProductMapper.INSTANCE.mapToProduct(productDTO);
            Product saved = productRepository.save(product);
            log.info("Product was created");
            return ProductMapper.INSTANCE.mapToProductDto(saved);
        }
    }

    @Override
    @Transactional
    public ProductDto updateProduct(String code, ProductDto productDTO) {
        log.info("update product with code {}", code);
        Product persistedProduct = productRepository.findByCode(code)
                .orElseThrow(ProductNotFoundException::new);
        Product updatedProduct = mapPresentProductFieldsProductDtoFields(persistedProduct, productDTO);
        Product saved = productRepository.save(updatedProduct);
        return ProductMapper.INSTANCE.mapToProductDto(saved);
    }

    @Override
    public Product mapPresentProductFieldsProductDtoFields(Product product, ProductDto productDto) {
        String name = productDto.getName();
        if (Objects.nonNull(name)) {
            product.setName(name);
        }
        String description = productDto.getDescription();
        if (Objects.nonNull(description)) {
            product.setDescription(description);
        }
        Double price = productDto.getPrice();
        if (Objects.nonNull(price)) {
            product.setPrice(price);
        }
        Double amount = productDto.getAmount();
        if (Objects.nonNull(amount)) {
            product.setAmount(amount);
        }
        return product;
    }

    @Override
    @Transactional
    public void deleteProduct(String code) {
        log.info("delete product by code {}", code);
        Product persistedProduct = productRepository.findByCode(code)
                .orElseThrow(ProductNotFoundException::new);
        productRepository.delete(persistedProduct);
        log.info("product with code {} was successfully deleted", code);
    }
}
