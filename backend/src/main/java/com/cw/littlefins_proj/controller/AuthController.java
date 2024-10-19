package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.dto.RequestResponse;
import com.cw.littlefins_proj.service.AuthService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    //register for an account
    @PostMapping("/signup")
    public ResponseEntity<RequestResponse> signUp(@Valid @RequestBody RequestResponse signUpRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(signUpRequest));
    }

    //signin to an account
    @PostMapping("/signin")
    public ResponseEntity<RequestResponse> signIn(@RequestBody RequestResponse signInRequest){
        return ResponseEntity.status(HttpStatus.OK).body(authService.signIn(signInRequest));
    }

    //refresh a token
    @PostMapping("/refresh")
    public ResponseEntity<RequestResponse> refreshToken(@RequestBody RequestResponse refreshTokenRequest){
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(refreshTokenRequest));
    }

}
