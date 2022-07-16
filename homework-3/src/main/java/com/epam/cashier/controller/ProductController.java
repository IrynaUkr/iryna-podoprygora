package com.epam.cashier.controller;

import com.epam.cashier.controller.dto.ProductDto;
import com.epam.cashier.controller.dto.group.OnCreate;
import com.epam.cashier.controller.dto.group.OnUpdate;
import com.epam.cashier.controller.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = "API description for SWAGGER documentation Product")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class ProductController {
    private final ProductService productService;

    @ApiOperation("get all products")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/product")
    public List<ProductDto> getAllproducts() {
        return productService.listProduct();
    }


    @ApiOperation("create product")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product")
    public ProductDto createProduct(@RequestBody @Validated(OnCreate.class) ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @ApiOperation("update product")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/product/{code}")
    public ProductDto updateProduct(@PathVariable String code,
                                    @RequestBody @Validated(OnUpdate.class) ProductDto productDto) {
        return productService.updateProduct(code, productDto);
    }

    @ApiOperation("get product by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/product/{id}")
    public ProductDto getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @ApiOperation("delete product by code")
    @DeleteMapping("/product/{code}")
    public ResponseEntity<Void> deleteUser(@PathVariable String code) {
        productService.deleteProduct(code);
        return ResponseEntity.noContent().build();
    }
}
