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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/user/api/voucher/redeem")
@CrossOrigin("*")
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

    //user redeem the voucher
    @PostMapping("/{id}")
    public ResponseEntity<Object> saveRedeemed(@PathVariable("id") Long voucherId) {

        User user = userService.findByEmail(authService.profile().getEmail());
        Optional<Voucher> voucher = voucherService.findById(voucherId);
        if (voucher.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        if(user.getCoin()<voucher.get().getValue()){
            return new ResponseEntity<>("Not enough coin!", HttpStatus.BAD_REQUEST);
        }

        user.setCoin(user.getCoin()-voucher.get().getValue());
        userService.save(user);

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

    //user use the voucher
    @PostMapping("/use/{id}")
    public ResponseEntity<Object> usevoucher(@PathVariable("id") Long voucherId) {
        Optional<Redeemed> findvoucher = redeemedService.findByVoucherId(voucherId);
        if (findvoucher.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        long randomNumber = ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);
        RedeemedResponse redeemedResponse=new RedeemedResponse();
        redeemedResponse.setVoucher(findvoucher.get().getVoucher());
        redeemedResponse.setRandomNum(randomNumber);
        findvoucher.get().setDate_used(LocalDate.now());
        redeemedResponse.setDateUsed(findvoucher.get().getDate_used());
        return new ResponseEntity<>(redeemedResponse, HttpStatus.OK);
    }


    //user get the stored redeemed vouchers
    @GetMapping
    public ResponseEntity<Object> findRedeemedByUserId() {
        User user = userService.findByEmail(authService.profile().getEmail());
        List<Redeemed>redeemedList=redeemedService.findByUserId(user.getId());
        if(redeemedList.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(redeemedList, HttpStatus.OK);
    }

    //****************** michelle ************************

    //michelle
    @PostMapping("/redeem")
    public Redeemed saveRedeemed(@RequestBody Redeemed redeemed) {
        return redeemedService.saveRedeemed(redeemed);
    }

    //michelle
    @PostMapping("/redeem/use")
    public boolean useVoucher(@RequestParam Long voucherId) {
        return redeemedService.useVoucher(voucherId);
    }


    //michelle
    @GetMapping("/redeem")
    public List<Redeemed> findRedeemedByUserId(@RequestParam Long userId) {
        return redeemedService.findRedeemedByUserId(userId);
    }



}
