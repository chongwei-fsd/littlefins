package com.cw.littlefins_proj.dto;

import com.cw.littlefins_proj.model.EnumRole;
import com.cw.littlefins_proj.model.User;
import com.cw.littlefins_proj.model.Voucher;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // ignore any properties in JSON input that are not bound to any fields during deserialization.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RedeemedResponse {
    private String message;
    private String username;
    private String email;
    private EnumRole role;
    private Long coin;
    private User user;
    private Voucher voucher;
}
