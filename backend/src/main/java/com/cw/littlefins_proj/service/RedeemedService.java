package com.cw.littlefins_proj.service;

import com.cw.littlefins_proj.model.Redeemed;
import com.cw.littlefins_proj.repo.RedeemedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import lombok.*;


@Service
public class RedeemedService {
    @Autowired
    RedeemedRepo redeemedRepo;


    public long count() {
        return redeemedRepo.count();
    }


    public void deleteById(Long id) {
        redeemedRepo.deleteById(id);
    }


    public Redeemed update(Redeemed redeemed) {
        return redeemedRepo.save(redeemed);
    }


    public Optional<Redeemed> findById(Long id) {
        return redeemedRepo.findById(id);
    }


    public List<Redeemed> findAll() {
        return redeemedRepo.findAll();
    }


    public Redeemed save(Redeemed redeemed) {
        return redeemedRepo.save(redeemed);
    }


    public List<Redeemed> findByUserId(Long userId) {
        return redeemedRepo.findByUserId(userId);
    }

    public Optional<Redeemed> findByVoucherId(Long voucherId) {
        return redeemedRepo.findByVoucherId(voucherId);
    }




}
