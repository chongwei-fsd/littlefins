package com.cw.littlefins_proj.exception;

public class NoResourceException extends RuntimeException {
    public NoResourceException() {
        super("Requested resource was not found.");
    }
}
