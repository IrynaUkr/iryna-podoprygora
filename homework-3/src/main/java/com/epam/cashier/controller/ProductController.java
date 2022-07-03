package com.epam.cashier.controller;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.dto.group.OnCreate;
import com.epam.cashier.controller.dto.group.OnUpdate;
import com.epam.cashier.controller.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/product")
    public List<ProductDto> getAllproducts() {
        return productService.listProduct();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product")
    public ProductDto createProduct(@RequestBody @Validated(OnCreate.class) ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/product/{id}")
    public ProductDto updateProduct(@PathVariable int id,
                                    @RequestBody @Validated(OnUpdate.class) ProductDto productDto ) {
        return productService.updateProduct(id, productDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/product/{id}")
    public ProductDto getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}