package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.exception.ResourceNotFoundException;
import com.cw.littlefins_proj.model.Redeemed;
import com.cw.littlefins_proj.model.Voucher;
import com.cw.littlefins_proj.service.RedeemedService;
import com.cw.littlefins_proj.service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/api/voucher")
@CrossOrigin("*")
public class VoucherController {
    @Autowired
    VoucherService voucherService;

    @Autowired
    RedeemedService redeemedService;

    //get available vouchers (haven't redeemed by user)
    @GetMapping
    public ResponseEntity<Object> allAvailableVouchers() {
        List<Voucher> availableVouchers = voucherService.getAvailableVouchers();
        if(availableVouchers.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(availableVouchers,HttpStatus.OK);
    }

    //admin add voucher(wish list)
    @PostMapping
    public ResponseEntity<Object> saveVoucher(@RequestBody @Valid Voucher voucher) {
        return new ResponseEntity<>(voucherService.save(voucher), HttpStatus.OK);
    }

    //admin update voucher(wish list)
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVoucher(@PathVariable("id") Long voucherId, @RequestBody @Valid Voucher voucher) {
        Voucher checkVoucher = voucherService.findById(voucherId).map(v -> {
            v.setDescription(voucher.getDescription());
            v.setImage(voucher.getImage());
            v.setExpDate(voucher.getExpDate());
            v.setValue(voucher.getValue());
            return voucherService.save(v);
        }).orElseThrow(() -> new ResourceNotFoundException());
        return new ResponseEntity<>(checkVoucher, HttpStatus.OK);
    }

    //admin delete voucher(wish list)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVoucher(@PathVariable("id") Long voucherId) {
        Voucher checkVoucher = voucherService.findById(voucherId).map(v -> {
            voucherService.deleteById(v.getId());
            return v;
        }).orElseThrow(() -> new ResourceNotFoundException());
        String response = String.format("Voucher %d deleted successfully", checkVoucher.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Object> countVoucher() {
        long count = voucherService.count();
        if (count <= 0) {
            return new ResponseEntity<>("No voucher found.", HttpStatus.NOT_FOUND);
        }
        Map<String, Object> totalVouchers = new HashMap<String, Object>();
        totalVouchers.put("total", count);
        return new ResponseEntity<>(totalVouchers, HttpStatus.OK);
    }


}
