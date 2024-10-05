package com.cw.littlefins_proj.service;

import com.cw.littlefins_proj.model.Redeemed;
import com.cw.littlefins_proj.repo.RedeemedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RedeemedService implements RedeemedServiceInterface {
    @Autowired
    RedeemedRepo redeemedRepo;

    @Override
    public long count() {
        return redeemedRepo.count();
    }

    @Override
    public void deleteById(Long id) {
        redeemedRepo.deleteById(id);
    }

    @Override
    public Redeemed update(Redeemed redeemed) {
        return redeemedRepo.save(redeemed);
    }

    @Override
    public Optional<Redeemed> findById(Long id) {
        return redeemedRepo.findById(id);
    }

    @Override
    public List<Redeemed> findAll() {
        return redeemedRepo.findAll();
    }

    @Override
    public Redeemed save(Redeemed redeemed) {
        return redeemedRepo.save(redeemed);
    }

    @Override
    public List<Redeemed> findByUserId(Long userId) {
        return redeemedRepo.findByUserId(userId);
    }


}
