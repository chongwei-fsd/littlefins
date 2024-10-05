package com.cw.littlefins_proj.repo;

import com.cw.littlefins_proj.model.Redeemed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RedeemedRepo extends JpaRepository<Redeemed,Long> {
    List<Redeemed> findByUserId(Long userId);
}
