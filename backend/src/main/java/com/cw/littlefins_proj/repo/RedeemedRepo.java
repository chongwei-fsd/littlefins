package com.cw.littlefins_proj.repo;

import com.cw.littlefins_proj.model.Redeemed;
import com.cw.littlefins_proj.service.RedeemedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RedeemedRepo extends JpaRepository<Redeemed,Long> {

    List<Redeemed> findByUserId(Long userId);
    Optional<Redeemed>findByVoucherId(Long voucherId);
}
