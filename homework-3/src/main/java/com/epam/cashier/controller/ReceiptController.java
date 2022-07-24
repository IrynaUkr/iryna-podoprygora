package com.epam.cashier.controller;

import com.epam.cashier.controller.dto.ReceiptDto;
import com.epam.cashier.controller.dto.ReceiptProductDto;
import com.epam.cashier.controller.dto.group.OnCreate;
import com.epam.cashier.controller.dto.group.OnUpdate;
import com.epam.cashier.controller.service.ReceiptService;
import com.epam.cashier.controller.service.model.ReceiptProducts;
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
@Api(tags = "API description for SWAGGER documentation Receipt")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class ReceiptController {
    private final ReceiptService receiptService;

    @ApiOperation("get all receipts")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/receipt")
    public List<ReceiptDto> getAllReceipts() {
        return receiptService.listReceipt();
    }

    @ApiOperation("create receipt")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/receipt")
    public ReceiptDto createNewReceipt(@RequestBody List<ReceiptProductDto> receiptProductDtos) {
        return receiptService.createReceipt(receiptProductDtos);
    }


    @ApiOperation("get receipt by number")
    @GetMapping(value = "/receipt/{number}")
    public ReceiptDto getReceipt(@PathVariable String number) {
        return receiptService.getReceipt(number);
    }

    @ApiOperation("delete receipt by number")
    @DeleteMapping("/receipt/{number}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable String number) {
        receiptService.deleteReceipt(number);
        return ResponseEntity.noContent().build();
    }
}
