package com.cw.littlefins_proj.service;

import com.cw.littlefins_proj.model.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherServiceInterface {
    public abstract Voucher save(Voucher voucher);
    public abstract List<Voucher> findAll();
    public abstract Optional<Voucher> findById(Long id);
    public abstract Voucher update(Voucher voucher);
    public abstract void deleteById(Long id);
    public abstract long count();

    //custom
    public abstract List<Voucher>getAvailableVouchers();
}
