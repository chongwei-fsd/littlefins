package com.cw.littlefins_proj.exception;

public class PasswordBlankException extends RuntimeException {
    public PasswordBlankException() {
        super("Password cannot be blank.");
    }
}
