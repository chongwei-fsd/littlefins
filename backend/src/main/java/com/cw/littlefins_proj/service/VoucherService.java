package com.cw.littlefins_proj.service;

import com.cw.littlefins_proj.model.Voucher;
import com.cw.littlefins_proj.repo.VoucherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {
    @Autowired
    VoucherRepo voucherRepo;


    public Voucher save(Voucher voucher) {
        return voucherRepo.save(voucher);
    }


    public List<Voucher> findAll() {
        return voucherRepo.findAll();
    }


    public Optional<Voucher> findById(Long id) {
        return voucherRepo.findById(id);
    }


    public Voucher update(Voucher voucher) {
        return voucherRepo.save(voucher);
    }


    public void deleteById(Long id) {
        voucherRepo.deleteById(id);
    }


    public long count() {
        return voucherRepo.count();
    }


    public List<Voucher> getAvailableVouchers() {
        return voucherRepo.findAllAvailableVouchers();
    }


    //****************** michelle ************************
    //michelle
    public List<Voucher> getAllVouchers() {
        // Implementation to fetch vouchers
        return List.of(new Voucher(null, "abc.jpg", "$5 abc eVoucher", 5L, LocalDate.of(2024, 12, 15)),
                new Voucher(null, "def.jpg", "$5 def eVoucher", 5L, LocalDate.of(2024, 12, 20)));
    }
}
