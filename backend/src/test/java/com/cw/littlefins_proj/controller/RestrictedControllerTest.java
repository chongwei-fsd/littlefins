package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.service.RestrictedService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class RestrictedControllerTest {

    //****************** michelle ************************

    @Mock
    private RestrictedService restrictedService; // Mock the service layer

    @InjectMocks
    private RestrictedController restrictedController; // Inject the controller


    @BeforeEach
    void setUp() {
        // Mock the service layer
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    //Michelle - 4. GET Get user profile localhost:8080/user/view
    //COMMENT from CW: IT WILL AFFECTS AUTHSERVICE REQUESTRESPONSE SIGNUP
//    @Test
//    void userView() {
//        // Mock user profile response
//        User mockUser = new User("testUser", "testuser@example.com", "password123", EnumRole.USER);
//        mockUser.setId(1L);
//        mockUser.setName("testUser");
//
//        when(restrictedService.getUserProfile(anyLong())).thenReturn(mockUser); // Mock the service call
//
//        User result = restrictedController.userView(1L); // Call the method
//
//        // Verify the result
//        assertNotNull(result);
//        assertEquals("testUser", result.getUsername());
//        verify(restrictedService).getUserProfile(1L); // Ensure the service method was called
//    }

    //Michelle - 7. POST User updates coin localhost:8080/user/updatecoin
    @Test
    void updateUserCoin() {
        Long userId = 1L;
        int newCoinAmount = 100;

        // Mock the service layer method to return true (indicating success)
        when(restrictedService.updateUserCoin(userId, newCoinAmount)).thenReturn(true);

        boolean result = restrictedController.updateUserCoin(userId, newCoinAmount); // Call the method

        // Assert the expected outcome
        assertTrue(result);
        verify(restrictedService).updateUserCoin(userId, newCoinAmount); // Verify the interaction
    }

}