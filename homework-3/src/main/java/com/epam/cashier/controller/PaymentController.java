package com.epam.cashier.controller;

import com.epam.cashier.controller.dto.PaymentDto;
import com.epam.cashier.controller.dto.group.OnCreate;
import com.epam.cashier.controller.dto.group.OnUpdate;
import com.epam.cashier.controller.service.PaymentService;
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
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Api(tags = "API description for SWAGGER documentation Payment")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class PaymentController {

    private final PaymentService paymentService;

    @ApiOperation("get all payments")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<PaymentDto> getAllPayments(){
        return paymentService.listPayment();
    }

    @ApiOperation("create payment")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public PaymentDto createPayment(@RequestBody @Validated(OnCreate.class) PaymentDto paymentDto){
        return paymentService.createPayment(paymentDto);
    }

    @ApiOperation("update payment")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{number}")
    public PaymentDto updatePayment(@PathVariable String number,
                                        @RequestBody @Validated(OnUpdate.class) PaymentDto paymentDto){
        return paymentService.updatePayment(number, paymentDto);
    }

    @ApiOperation("get payment by number")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{number}")
    public PaymentDto getPayment(@PathVariable String number){
        return paymentService.getPaymentByNumber(number);
    }

    @ApiOperation("delete payment by number")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{number}")
    public ResponseEntity<Void> deletePayment(@PathVariable String number) {
        paymentService.deletePaymentByNumber(number);
        return ResponseEntity.noContent().build();
    }
}
