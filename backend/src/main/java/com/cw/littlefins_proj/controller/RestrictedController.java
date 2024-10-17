package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.dto.RequestResponse;
import com.cw.littlefins_proj.model.User;
import com.cw.littlefins_proj.model.Voucher;
import com.cw.littlefins_proj.repo.UserRepo;
import com.cw.littlefins_proj.repo.VoucherRepo;
import com.cw.littlefins_proj.service.AuthService;
import com.cw.littlefins_proj.service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class RestrictedController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VoucherService voucherService;

    // restricted view for user only
    @GetMapping("/user/view")
    public ResponseEntity<Object> userView(){
        return ResponseEntity.status(HttpStatus.OK).body(authService.profile());
    }

    //update user coin
    @PostMapping("/user/updatecoin")
    public ResponseEntity<Object> updateUserCoin(@RequestBody RequestResponse request){
        return ResponseEntity.status(HttpStatus.OK).body(authService.updateCoin(request));
    }

    // restricted view for administrator only
    @GetMapping("/admin/view")
    public ResponseEntity<Object> adminView(){
        return ResponseEntity.status(HttpStatus.OK).body(authService.profile());
    }

    // user update profile
    @PutMapping("/user/updateprofile")
    public ResponseEntity<Object> updateProfile(@Valid @RequestBody RequestResponse request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK).body(authService.updateProfile(userName,request));
    }

    // restricted view for user or view profile info.
    @GetMapping("/restricted/profile")
    public ResponseEntity<Object> profileView(){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.profile());
    }

    @GetMapping("/restricted/vouchers")
    public ResponseEntity<Object> adminGetVouchers(){
        List<Voucher>allVouchers=voucherService.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(allVouchers);
    }

}
