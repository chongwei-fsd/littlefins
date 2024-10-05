package com.cw.littlefins_proj.controller;

import com.cw.littlefins_proj.model.Voucher;
import com.cw.littlefins_proj.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/voucher")
public class VoucherController {
    @Autowired
    VoucherService voucherService;

    @GetMapping
    public ResponseEntity<Object> allVouchers() {
        return new ResponseEntity<>(voucherService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveVoucher(@RequestBody Voucher voucher) {
        return new ResponseEntity<>(voucherService.save(voucher), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVoucher(@PathVariable("id") Long voucherId, @RequestBody Voucher voucher) {
        Optional<Voucher> checkVoucher = voucherService.findById(voucherId).map(v -> {
            v.setDescription(voucher.getDescription());
            v.setImage(voucher.getImage());
            v.setExpDate(voucher.getExpDate());
            v.setValue(voucher.getValue());
            return voucherService.save(v);
        });
        if (checkVoucher.isEmpty()) {
            return new ResponseEntity<>("Voucher not updated", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(checkVoucher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVoucher(@PathVariable("id") Long voucherId) {
        Optional<Voucher> checkVoucher = voucherService.findById(voucherId).map(v -> {
            voucherService.deleteById(v.getId());
            return v;
        });
        if (checkVoucher.isEmpty()) {
            return new ResponseEntity<>("Voucher not deleted", HttpStatus.BAD_REQUEST);
        }
        String response = String.format("Voucher %d deleted successfully", checkVoucher.get().getId());
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
