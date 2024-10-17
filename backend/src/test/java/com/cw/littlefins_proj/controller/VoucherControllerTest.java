package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.model.User;
import com.cw.littlefins_proj.model.Voucher;
import com.cw.littlefins_proj.service.VoucherService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VoucherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoucherService voucherService;

    User user;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    //Michelle - 3. GET Get available vouchers localhost:8080/user/api/voucher
    @Test
    void testAllAvailableVouchers() throws Exception {
        // Arrange
        List<Voucher> vouchers = List.of(
                new Voucher(1L, "image1.png", "$5 Popular eVoucher", 5L, LocalDate.of(2024, 12, 31)),
                new Voucher(2L, "image2.png", "$5 Liho eVoucher", 5L, LocalDate.of(2024, 12, 31))
        );
        when(voucherService.getAvailableVouchers()).thenReturn(vouchers);

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb3NoQGdtYWlsLmNvbSIsImlhdCI6MTcyOTA3MDM1MywiZXhwIjoxNzI5MTU2NzUzfQ.wvpGZesX_IKonrGaAmhPCgwDMT1dsNJqbOYNwUfXi3k";

        // Act
        ResultActions resultActions = mockMvc.perform(get("/user/api/voucher")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token));

        // Assert
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.length()").value(vouchers.size()))
                .andExpect(jsonPath("$[0].id").value(vouchers.get(0).getId()))
                .andExpect(jsonPath("$[0].image").value(vouchers.get(0).getImage()))
                .andExpect(jsonPath("$[0].description").value(vouchers.get(0).getDescription()))
                .andExpect(jsonPath("$[0].value").value(vouchers.get(0).getValue()))
                .andExpect(jsonPath("$[0].expDate").value(vouchers.get(0).getExpDate().toString()))
                .andExpect(jsonPath("$[1].id").value(vouchers.get(1).getId()))
                .andExpect(jsonPath("$[1].image").value(vouchers.get(1).getImage()))
                .andExpect(jsonPath("$[1].description").value(vouchers.get(1).getDescription()))
                .andExpect(jsonPath("$[1].value").value(vouchers.get(1).getValue()))
                .andExpect(jsonPath("$[1].expDate").value(vouchers.get(1).getExpDate().toString()));
    }

}