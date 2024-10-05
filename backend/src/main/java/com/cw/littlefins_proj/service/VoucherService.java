package com.cw.littlefins_proj.service;

import com.cw.littlefins_proj.model.Voucher;
import com.cw.littlefins_proj.repo.VoucherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherService implements VoucherServiceInterface {
    @Autowired
    VoucherRepo voucherRepo;

    @Override
    public Voucher save(Voucher voucher) {
        return voucherRepo.save(voucher);
    }

    @Override
    public List<Voucher> findAll() {
        return voucherRepo.findAll();
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return voucherRepo.findById(id);
    }

    @Override
    public Voucher update(Voucher voucher) {
        return voucherRepo.save(voucher);
    }

    @Override
    public void deleteById(Long id) {
        voucherRepo.deleteById(id);
    }

    @Override
    public long count() {
        return voucherRepo.count();
    }
}
