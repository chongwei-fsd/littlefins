package com.cw.littlefins_proj.service;

import com.cw.littlefins_proj.model.User;
import com.cw.littlefins_proj.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;


    public User save(User user) {
        return userRepo.save(user);
    }


    public List<User> findAll() {
        return userRepo.findAll();
    }


    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }


    public User update(User user) {
        return userRepo.save(user);
    }


    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }


    public long count() {
        return userRepo.count();
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }


}
