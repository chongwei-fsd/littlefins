package com.cw.littlefins_proj.repo;

import com.cw.littlefins_proj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
