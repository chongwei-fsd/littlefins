package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.exception.ResourceNotFoundException;
import com.cw.littlefins_proj.model.Voucher;
import com.cw.littlefins_proj.service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/voucher/{id}")
    public ResponseEntity<Object>getVoucherById(@PathVariable("id")Long voucherId){
        Optional<Voucher> checkVoucher=voucherService.findById(voucherId);
        if(checkVoucher.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(checkVoucher.get(), HttpStatus.OK);
    }

    @PostMapping("/voucher")
    public ResponseEntity<Object> addVoucher(@RequestBody @Valid Voucher voucher) {
        return new ResponseEntity<>(voucherService.save(voucher), HttpStatus.OK);
    }

    @PutMapping("/voucher/{id}")
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

    @DeleteMapping("/voucher/{id}")
    public ResponseEntity<Object> deleteVoucher(@PathVariable("id") Long voucherId) {
        Voucher checkVoucher = voucherService.findById(voucherId).map(v -> {
            voucherService.deleteById(v.getId());
            return v;
        }).orElseThrow(() -> new ResourceNotFoundException());
        String response = String.format("Voucher %d deleted successfully", checkVoucher.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
