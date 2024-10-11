package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.dto.RequestResponse;
import com.cw.littlefins_proj.model.User;
import com.cw.littlefins_proj.repo.UserRepo;
import com.cw.littlefins_proj.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class RestrictedController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepo userRepo;

    // restricted view for user only
    @GetMapping("/user/view")
    public ResponseEntity<Object> userView(){
        return ResponseEntity.status(HttpStatus.OK).body(authService.profile());
    }

    // restricted view for administrator only
    @GetMapping("/admin/view")
    public ResponseEntity<Object> adminView(){
        return ResponseEntity.status(HttpStatus.OK).body(authService.profile());
    }

    // restricted view for user or administrator to update profile info.
    @PostMapping("/restricted/updateprofile")
    public ResponseEntity<Object> updateProfile(@RequestBody RequestResponse request){
        // TODO: Implement the statements to allow the user to update the profile
        return ResponseEntity.status(HttpStatus.OK).body(authService.updateProfile(request));
    }

    // restricted view for user or view profile info.
    @GetMapping("/restricted/profile")
    public ResponseEntity<Object> profileView(){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.profile());
    }

}
