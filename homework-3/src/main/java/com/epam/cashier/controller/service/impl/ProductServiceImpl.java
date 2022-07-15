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

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDto getProduct(int id) {
        log.info("Get Product by id: " + id);
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        log.info("Product with" + id + " was found");
        return ProductMapper.INSTANCE.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> listProduct() {
        log.info("Find list of Product");
        List<Product> allProducts = productRepository.findAll();
        return ProductMapper.INSTANCE.mapToProductDtos(allProducts);
    }

    @Override
    public ProductDto createProduct(ProductDto productDTO) {
        log.info("create product with code" + productDTO.getCode());
        if(productRepository.existsByCode(productDTO.getCode())){
            throw new ProductAlreadyExistException();
        }else{
            Product product = ProductMapper.INSTANCE.mapToProduct(productDTO);
            productRepository.save(product);
            log.info("Product was created");
            return ProductMapper.INSTANCE.mapToProductDto(product);
        }
    }

    @Override
    public ProductDto updateProduct(int id, ProductDto productDTO) {
        log.info("update product with id {}", id);
        Product pr = ProductMapper.INSTANCE.mapToProduct(productDTO);
        pr.setProductId(id);
        return ProductMapper.INSTANCE.mapToProductDto(pr);
    }

    @Override
    public void deleteProduct(int id) {
        log.info("deleteUser with email {}", id);
        productRepository.deleteById(id);
    }
}
