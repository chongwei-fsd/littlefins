package com.cw.littlefins_proj.service;

import com.cw.littlefins_proj.model.Voucher;
import com.cw.littlefins_proj.repo.VoucherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
