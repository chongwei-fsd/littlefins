package com.cw.littlefins_proj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;
import jakarta.validation.constraints.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String image;

    @Column
    @NotBlank(message = "Product desc cannot be blank.")
    @Size(min = 3, message = "Product Desc. must be at least 3 characters.")
    @Size(max = 255, message = "Supplier SKU must not be more than 255 characters.")
    private String description;

    @Column
    private Long value;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expDate = LocalDate.of(2024, 12, 31);

}
