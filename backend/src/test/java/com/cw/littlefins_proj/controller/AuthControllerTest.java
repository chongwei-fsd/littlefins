package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.dto.RequestResponse;
import com.cw.littlefins_proj.model.EnumRole;
import com.cw.littlefins_proj.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    // 1. POST Sign up localhost:8080/auth/api/signup
    @Test
    void testSignUp() throws Exception {
        //Given
        RequestResponse signUpRequest = new RequestResponse();
        signUpRequest.setName("michelle");
        signUpRequest.setEmail("michelle@gmail.com");
        signUpRequest.setPassword("michelle123");
        signUpRequest.setRole(EnumRole.valueOf("USER"));

        RequestResponse expectedResponse = new RequestResponse();
        expectedResponse.setMessage("User saved successfully.");

        //When
        Mockito.when(authService.signUp(Mockito.any(RequestResponse.class)))
                .thenReturn(expectedResponse);

        // Perform the request
        ResultActions resultActions = mockMvc.perform(post("/auth/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)));

        // Then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("User saved successfully."));

        Mockito.verify(authService, Mockito.times(1)).signUp(Mockito.any(RequestResponse.class));
    }

    // 2. POST Sign in localhost:8080/auth/api/signin
    @Test
    void testSignIn() throws Exception {
        // Given
        RequestResponse signInRequest = new RequestResponse();
        signInRequest.setEmail("michelle@gmail.com");
        signInRequest.setPassword("michelle123");

        RequestResponse expectedResponse = new RequestResponse();
        expectedResponse.setToken("mock-jwt-token");
        expectedResponse.setRefreshToken("mock-refresh-token");
        expectedResponse.setExpirationTime("24Hr");
        expectedResponse.setMessage("Signed in successfully.");

        // When
        Mockito.when(authService.signIn(Mockito.any(RequestResponse.class)))
                .thenReturn(expectedResponse);

        // Perform the request
        ResultActions resultActions = mockMvc.perform(post("/auth/api/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signInRequest)));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mock-jwt-token"))
                .andExpect(jsonPath("$.refreshToken").value("mock-refresh-token"))
                .andExpect(jsonPath("$.expirationTime").value("24Hr"))
                .andExpect(jsonPath("$.message").value("Signed in successfully."));

        Mockito.verify(authService, Mockito.times(1)).signIn(Mockito.any(RequestResponse.class));
    }
}