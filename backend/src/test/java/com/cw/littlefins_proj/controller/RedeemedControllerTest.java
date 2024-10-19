package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.model.Redeemed;
import com.cw.littlefins_proj.model.Voucher;
import com.cw.littlefins_proj.service.RedeemedService;
import com.cw.littlefins_proj.service.VoucherService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RedeemedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RedeemedService redeemedService;

    @Mock
    private VoucherService voucherService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    // michelle - 5. POST Redeem voucher localhost:8080/user/api/voucher/redeem
    @Test
    void saveRedeemed() {
    }

    // michelle - 8. POST User uses voucher localhost:8080/user/api/voucher/redeem/use
    // got error, need to check with martin
    @Test
    void usevoucher() throws Exception {
    }

    // michelle - 6. GET Get redeemed vouchers localhost:8080/user/api/voucher/redeem
    @Test
    void findRedeemedByUserId()throws Exception {
        MvcResult result = mockMvc.perform(get("/user/api/voucher/redeem")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWNoZWxsZUBnbWFpbC5jb20iLCJpYXQiOjE3MjkzMzYzMzYsImV4cCI6MTcyOTQyMjczNn0.8jVbY5y-2BGn0Z5CgC_ClPwPkAmMtsZN39dbNDiIwec")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].voucher.id").value(3))
                .andExpect(jsonPath("$.[0].voucher.image").value("popular.jpg"))
                .andExpect(jsonPath("$.[0].voucher.description").value("$5 Popular eVoucher"))
                .andExpect(jsonPath("$.[0].voucher.value").value(5))
                .andExpect(jsonPath("$.[0].voucher.expDate").value("2024-12-31"))
                .andReturn();
    }

}
