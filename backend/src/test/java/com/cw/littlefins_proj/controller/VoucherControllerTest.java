package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.model.Voucher;
import com.cw.littlefins_proj.service.VoucherService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class VoucherControllerTest {

    //****************** michelle ************************

    @Mock
    private VoucherService voucherService; // Mock the VoucherService

    @InjectMocks
    private VoucherController voucherController; // Inject the VoucherController

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    //Michelle - 3. GET Get available vouchers localhost:8080/user/api/voucher
    @Test
    void allVouchers() {
        // Create a list of mock vouchers
        List<Voucher> mockVouchers = new ArrayList<>();
        mockVouchers.add(new Voucher(null,"abc.jpg","$5 abc eVoucher",5L,LocalDate.of(2024,12,15)));
        mockVouchers.add(new Voucher(null,"def.jpg","$5 def eVoucher",5L,LocalDate.of(2024,12,20)));

        // Mock the service method
        when(voucherService.getAllVouchers()).thenReturn(mockVouchers);

        // Call the controller method
        List<Voucher> result = voucherController.getAllVouchers();

        // Verify the results
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("$5 abc eVoucher", result.get(0).getDescription());
        assertEquals("$5 def eVoucher", result.get(1).getDescription());
        verify(voucherService).getAllVouchers(); // Ensure the service method was called
    }

}