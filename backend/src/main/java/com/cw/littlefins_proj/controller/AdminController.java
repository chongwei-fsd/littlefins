package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping("/vouchers")
    public ResponseEntity<Object> userView(){
        return ResponseEntity.status(HttpStatus.OK).body(voucherService.findAll());
    }
}
