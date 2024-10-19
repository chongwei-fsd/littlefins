package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.model.User;
import com.cw.littlefins_proj.repo.UserRepo;
import com.cw.littlefins_proj.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RestrictedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @Mock
    private UserRepo userRepository;

    @InjectMocks
    private AuthController authController;

    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    //Michelle - 4. GET Get user profile localhost:8080/user/view
    @Test
    void userView() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/view")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWNoZWxsZUBnbWFpbC5jb20iLCJpYXQiOjE3MjkzMzYzMzYsImV4cCI6MTcyOTQyMjczNn0.8jVbY5y-2BGn0Z5CgC_ClPwPkAmMtsZN39dbNDiIwec")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Michelle"))
                .andExpect(jsonPath("$.email").value("michelle@gmail.com"))
                .andExpect(jsonPath("$.password").value("$2a$10$FgAwfnZWIgaGCagS6j458eeoDgmlir9hXEJpIdL6dLc47ilGVSp.O"))
                .andExpect(jsonPath("$.coin").value(10))
                .andReturn();
    }

    //Michelle - 7. POST User updates coin localhost:8080/user/updatecoin
    @Test
    void updateUserCoin() throws Exception {
        //check the db for michelle coin is 10? eg.current is 10
        MvcResult result = mockMvc.perform(post("/user/updatecoin")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWNoZWxsZUBnbWFpbC5jb20iLCJpYXQiOjE3MjkzMzYzMzYsImV4cCI6MTcyOTQyMjczNn0.8jVbY5y-2BGn0Z5CgC_ClPwPkAmMtsZN39dbNDiIwec")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"coin\": 10}") //added 10
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Coin updated successfully."))
                .andExpect(jsonPath("$.user.coin").value(20)) //eg. 10+10=20
                .andReturn();
    }
}