package com.cw.littlefins_proj.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDate;

@Entity
public class Redeemed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @Column
    private Boolean is_used=false;

    @Column
    private Boolean is_redeemed=false;

    @Column
    private LocalDate date_used;

    public Redeemed() {
    }

    public Redeemed(User user, Voucher voucher) {
        this.user = user;
        this.voucher = voucher;
    }

    public Redeemed(User user, Voucher voucher, Boolean is_used, Boolean is_redeemed, LocalDate date_used) {
        this.user = user;
        this.voucher = voucher;
        this.is_used = is_used;
        this.is_redeemed = is_redeemed;
        this.date_used = date_used;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getIs_used() {
        return is_used;
    }

    public void setIs_used(Boolean is_used) {
        this.is_used = is_used;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public Boolean getIs_redeemed() {
        return is_redeemed;
    }

    public void setIs_redeemed(Boolean is_redeemed) {
        this.is_redeemed = is_redeemed;
    }

    public LocalDate getDate_used() {
        return date_used;
    }

    public void setDate_used(LocalDate date_used) {
        this.date_used = date_used;
    }
}
