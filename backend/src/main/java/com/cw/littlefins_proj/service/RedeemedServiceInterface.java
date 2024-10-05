package com.cw.littlefins_proj.service;

import com.cw.littlefins_proj.model.Redeemed;

import java.util.List;
import java.util.Optional;

public interface RedeemedServiceInterface {
    public abstract Redeemed save(Redeemed redeemed);
    public abstract List<Redeemed> findAll();
    public abstract Optional<Redeemed> findById(Long id);
    public abstract Redeemed update(Redeemed redeemed);
    public abstract void deleteById(Long id);
    public abstract long count();
    public abstract List<Redeemed> findByUserId(Long userId);
}
