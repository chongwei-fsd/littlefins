package com.cw.littlefins_proj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String image;

    @Column
    private String description;

    @Column
    private Long value;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expDate = LocalDate.of(2024, 12, 31);

    public Voucher() {
    }

    public Voucher(String image, String description, Long value, LocalDate expDate) {
        this.image = image;
        this.description = description;
        this.value = value;
        this.expDate = expDate;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
}
