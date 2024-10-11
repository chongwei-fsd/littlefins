package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.dto.RedeemedResponse;
import com.cw.littlefins_proj.dto.RequestResponse;
import com.cw.littlefins_proj.exception.ResourceNotFoundException;
import com.cw.littlefins_proj.model.Redeemed;
import com.cw.littlefins_proj.model.User;
import com.cw.littlefins_proj.model.Voucher;
import com.cw.littlefins_proj.repo.RedeemedRepo;
import com.cw.littlefins_proj.service.AuthService;
import com.cw.littlefins_proj.service.RedeemedService;
import com.cw.littlefins_proj.service.UserService;
import com.cw.littlefins_proj.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/api/voucher/redeem")
public class RedeemedController {
    @Autowired
    RedeemedService redeemedService;

    @Autowired
    UserService userService;

    @Autowired
    VoucherService voucherService;

    @Autowired
    RedeemedRepo redeemedRepo;

    @Autowired
    AuthService authService;

    @GetMapping
    public ResponseEntity<Object> allRedeemed() {
        List<Redeemed> redeemedList = redeemedService.findAll();
        if (redeemedList.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(redeemedList, HttpStatus.OK);
    }

    //redeem voucher
    @PostMapping("/{id}")
    public ResponseEntity<Object> saveRedeemed(@PathVariable("id") Long voucherId) {

        User user = userService.findByEmail(authService.profile().getEmail());
        Optional<Voucher> voucher = voucherService.findById(voucherId);
        if (voucher.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        Redeemed redeemed = new Redeemed();
        redeemed.setUser(user);
        redeemed.setVoucher(voucher.get());

        Redeemed confirmRedeemed=redeemedService.save(redeemed);

        RedeemedResponse redeemedResponse=new RedeemedResponse();

        redeemedResponse.setMessage("Voucher successfully redeemed.");
        redeemedResponse.setUsername(confirmRedeemed.getUser().getUsername());
        redeemedResponse.setEmail(confirmRedeemed.getUser().getEmail());
        redeemedResponse.setRole(confirmRedeemed.getUser().getRole());
        redeemedResponse.setCoin(confirmRedeemed.getUser().getCoin());
        redeemedResponse.setVoucher(confirmRedeemed.getVoucher());

        return new ResponseEntity<>(redeemedResponse, HttpStatus.OK);
    }


    //find Redeemed By UserId
    @GetMapping("/{id}")
    public ResponseEntity<Object> findRedeemedByUserId(@PathVariable("id") Long userId) {
        List<Redeemed> RedeemedList = redeemedService.findByUserId(userId);
        if (RedeemedList.isEmpty()) {
            return new ResponseEntity<>("No redeemed vouchers found for user", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(RedeemedList, HttpStatus.OK);

    }


}
