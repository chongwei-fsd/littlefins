package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.model.Redeemed;
import com.cw.littlefins_proj.service.RedeemedService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class RedeemedControllerTest {

    //****************** michelle ************************

    @Mock
    private RedeemedService redeemedService;

    @InjectMocks
    private RedeemedController redeemedController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    // michelle - 5. POST Redeem voucher localhost:8080/user/api/voucher/redeem
    @Test
    void saveRedeemed() {
        Redeemed redeemed = new Redeemed(); // Create an instance with necessary fields
        when(redeemedService.saveRedeemed(any(Redeemed.class))).thenReturn(redeemed);

        Redeemed result = redeemedController.saveRedeemed(redeemed);

        assertNotNull(result);
        assertEquals(redeemed, result);
        verify(redeemedService).saveRedeemed(redeemed);
    }

    // michelle - 8. POST User uses voucher localhost:8080/user/api/voucher/redeem/use
    @Test
    void useVoucher() {
        Long voucherId = 1L; // Example ID
        when(redeemedService.useVoucher(voucherId)).thenReturn(true); // Assuming useVoucher returns boolean

        boolean result = redeemedController.useVoucher(voucherId);

        assertTrue(result);
        verify(redeemedService).useVoucher(voucherId);
    }

    // michelle - 6. GET Get redeemed vouchers localhost:8080/user/api/voucher/redeem
    @Test
    void findRedeemedByUserId() {
        Long userId = 1L; // Example user ID
        List<Redeemed> redeemedList = new ArrayList<>(); // Create a list of redeemed vouchers
        when(redeemedService.findRedeemedByUserId(userId)).thenReturn(redeemedList);

        List<Redeemed> result = redeemedController.findRedeemedByUserId(userId);

        assertNotNull(result);
        assertEquals(redeemedList, result);
        verify(redeemedService).findRedeemedByUserId(userId);
    }

}
