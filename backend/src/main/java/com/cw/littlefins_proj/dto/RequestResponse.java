package com.cw.littlefins_proj.dto;

import com.cw.littlefins_proj.model.EnumRole;
import com.cw.littlefins_proj.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // ignore any properties in JSON input that are not bound to any fields during deserialization.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestResponse {
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String email;
    private EnumRole role;
    private String password;
    private Long coin;
    private User user;
    private LocalDate dob;
}
