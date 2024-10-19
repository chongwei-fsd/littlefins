package com.cw.littlefins_proj.repo;

import com.cw.littlefins_proj.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface VoucherRepo extends JpaRepository<Voucher,Long> {

    @Query("SELECT v FROM Voucher v WHERE v.id NOT IN (SELECT r.voucher.id FROM Redeemed r)")
    List<Voucher> findAllAvailableVouchers();

}
