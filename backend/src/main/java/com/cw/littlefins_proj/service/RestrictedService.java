package com.cw.littlefins_proj.service;

import com.cw.littlefins_proj.model.User;
import com.cw.littlefins_proj.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestrictedService {
    @Autowired
    private UserRepo userRepo;

    public User getUserProfile(Long id) {
        return userRepo.findById(id).orElse(null); // Fetch user from repository
    }

    public boolean updateUserCoin(Long userId, int newCoinAmount) {
        // Implementation to update user coins
        return true; // Dummy return for illustration
    }
}
