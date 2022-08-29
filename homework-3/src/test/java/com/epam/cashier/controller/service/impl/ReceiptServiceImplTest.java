package com.epam.cashier.controller.service.impl;

import com.epam.cashier.controller.dto.ReceiptDto;
import com.epam.cashier.controller.service.exception.ReceiptNotFoundException;
import com.epam.cashier.controller.service.mapper.ReceiptMapper;
import com.epam.cashier.controller.service.model.Receipt;
import com.epam.cashier.controller.service.repository.ReceiptRepository;
import com.epam.cashier.controller.service.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.cashier.controller.service.test.util.TestDataUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReceiptServiceImplTest {

    @InjectMocks
    private ReceiptServiceImpl receiptService;

    @Mock
    private ReceiptRepository receiptRepository;


    @Test
    void getReceiptByIdTest() {
        Receipt receipt = TestDataUtil.createReceipt();
        ReceiptDto testReceiptDto = ReceiptMapper.INSTANCE.mapToReceiptDto(receipt);
        when(receiptRepository.findByReceiptId(RECEIPT_ID)).thenReturn(Optional.of(receipt));
        ReceiptDto receiptById = receiptService.getReceiptById(RECEIPT_ID);
        assertThat(testReceiptDto, allOf(
                hasProperty("number", equalTo(receiptById.getNumber())),
                hasProperty("receiptId", equalTo(receiptById.getReceiptId())),
                hasProperty("status", equalTo(receiptById.getStatus()))
        ));
    }

    @Test
    void getReceiptReceiptNotFoundException() {
        when(receiptRepository.findByReceiptId(RECEIPT_ID)).thenReturn(Optional.empty());
        assertThrows(ReceiptNotFoundException.class,
                () -> receiptService.getReceiptById(RECEIPT_ID));
    }

    @Test
    void listReceiptTest() {
        List<Receipt> listReceipt = createListReceipt();
        when(receiptRepository.findAll()).thenReturn(listReceipt);

        List<ReceiptDto> dtoList = receiptService.listReceipt();
        assertThat(dtoList.size(), equalTo(listReceipt.size()));
    }

    @Test
    void updateReceiptTest() {
        Receipt testReceipt = createReceipt();
        ReceiptDto testReceiptDto = createReceiptDto();
        when(receiptRepository.findByNumber(RECEIPT_NUMBER)).thenReturn(Optional.of(testReceipt));

        ReceiptDto receiptDto = receiptService.updateReceipt(RECEIPT_NUMBER, testReceiptDto);
        assertThat(testReceiptDto, allOf(
                hasProperty("number", equalTo(receiptDto.getNumber())),
                hasProperty("status", equalTo(receiptDto.getStatus()))
        ));
    }

    @Test
    void updateReceiptReceiptNotFoundExceptionTest() {
        ReceiptDto receiptDto = createReceiptDto();
        when(receiptRepository.findByNumber(RECEIPT_NUMBER)).thenReturn(Optional.empty());
        assertThrows(ReceiptNotFoundException.class,
                () -> receiptService.updateReceipt(RECEIPT_NUMBER, receiptDto));

    }

    @Test
    void deleteReceiptByNumber() {
        Receipt testReceipt = createReceipt();
        when(receiptRepository.findByNumber(RECEIPT_NUMBER)).thenReturn(Optional.of(testReceipt));

        receiptService.deleteReceiptByNumber(RECEIPT_NUMBER);
        verify(receiptRepository, times(1)).delete(testReceipt);
    }

    @Test
    public void deleteReceiptNotFoundExceptionTest() {
        when(receiptRepository.findByNumber(RECEIPT_NUMBER)).thenReturn(Optional.empty());

        assertThrows(ReceiptNotFoundException.class,
                () -> receiptService.deleteReceiptByNumber(RECEIPT_NUMBER));
    }
}