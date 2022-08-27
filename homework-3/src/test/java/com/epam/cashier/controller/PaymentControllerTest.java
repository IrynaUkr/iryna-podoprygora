package com.epam.cashier.controller;

import com.epam.cashier.controller.dto.PaymentDto;
import com.epam.cashier.controller.service.PaymentService;
import com.epam.cashier.controller.service.test.config.TestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.epam.cashier.controller.service.test.util.TestDataUtil.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentController.class)
@Import(TestConfig.class)
class PaymentControllerTest {
    @MockBean
    private PaymentService paymentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllPaymentsTest() throws Exception {
        List<PaymentDto> paymentDtosList = createPaymentDtosList();
        when(paymentService.listPayment()).thenReturn(paymentDtosList);

        mockMvc.perform(get("/api/v1/payment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createPaymentTest() throws Exception {
        PaymentDto paymentDto = createPaymentDto();
        when(paymentService.createPayment(paymentDto)).thenReturn(paymentDto);

        mockMvc.perform(post("/api/v1/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number").value(PAYMENT_NUMBER));
    }

    @Test
    void updatePaymentTest() throws Exception{
        PaymentDto paymentDto = createPaymentDto();
        when(paymentService.updatePayment(PAYMENT_NUMBER, paymentDto)).thenReturn(paymentDto);
        mockMvc.perform(patch("/api/v1/payment/" + PAYMENT_NUMBER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number").value(PAYMENT_NUMBER));
    }

    @Test
    void getPaymentTest() throws Exception{
        PaymentDto paymentDto = createPaymentDto();
        when(paymentService.getPaymentByNumber(PAYMENT_NUMBER)).thenReturn(paymentDto);

        mockMvc.perform(get("/api/v1/payment/" + PAYMENT_NUMBER))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number").value(PAYMENT_NUMBER));
    }

    @Test
    void deletePayment() throws Exception{
        paymentService.deletePaymentByNumber(PAYMENT_NUMBER);
        mockMvc.perform(delete("/api/v1/payment/" + PAYMENT_NUMBER))
                .andExpect(status().isNoContent());
    }
}