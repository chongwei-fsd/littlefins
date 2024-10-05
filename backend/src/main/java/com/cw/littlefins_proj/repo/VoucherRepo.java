package com.cw.littlefins_proj.repo;

import com.cw.littlefins_proj.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepo extends JpaRepository<Voucher,Long> {
}
