package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.model.Redeemed;
import com.cw.littlefins_proj.model.User;
import com.cw.littlefins_proj.model.Voucher;
import com.cw.littlefins_proj.service.RedeemedService;
import com.cw.littlefins_proj.service.UserService;
import com.cw.littlefins_proj.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/redeemed")
public class RedeemedController {
    @Autowired
    RedeemedService redeemedService;

    @Autowired
    UserService userService;

    @Autowired
    VoucherService voucherService;

    @GetMapping
    public ResponseEntity<Object> allRedeemed() {
        return new ResponseEntity<>(redeemedService.findAll(), HttpStatus.OK);
    }

    //add voucher
    @PostMapping("/{uId}/{vId}")
    public ResponseEntity<Object> saveRedeemed(@PathVariable("uId") Long userId, @PathVariable("vId") Long voucherId) {
        Optional<User> user = userService.findById(userId);
        Optional<Voucher> voucher = voucherService.findById(voucherId);
        if(user.isEmpty() || voucher.isEmpty()){
            return new ResponseEntity<>("Redemption failed",HttpStatus.BAD_REQUEST);
        }
        Redeemed newRedeemed=new Redeemed(user.get(),voucher.get());
        return new ResponseEntity<>(redeemedService.save(newRedeemed),HttpStatus.OK);
    }

    //find Redeemed By UserId
    @GetMapping("/{id}")
    public ResponseEntity<Object> findRedeemedByUserId(@PathVariable("id")Long userId) {
        List<Redeemed>RedeemedList=redeemedService.findByUserId(userId);
        if(RedeemedList.isEmpty()){
            return new ResponseEntity<>("No redeemed vouchers found for user",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(RedeemedList,HttpStatus.OK);

    }


}
