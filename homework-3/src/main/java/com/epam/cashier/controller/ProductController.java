package com.epam.cashier.controller;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<ProductDto> getAllproducts() {
        return productService.listProduct();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public ProductDto updateProduct(@PathVariable int id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
