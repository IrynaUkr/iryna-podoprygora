package com.epam.cashier.controller;

import com.epam.cashier.controller.service.ReceiptService;
import com.epam.cashier.controller.service.test.config.TestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReceiptControllerTest.class)
@Import(TestConfig.class)
class ReceiptControllerTest {
    @MockBean
    private ReceiptService receiptService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllReceipts() {
    }

    @Test
    void createNewReceipt() {
    }

    @Test
    void getReceipt() {
    }

    @Test
    void deleteReceipt() {
    }
}