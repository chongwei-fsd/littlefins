package com.cw.littlefins_proj.exception;

public class MessageNotReadableException extends RuntimeException {
    //constructor
    public MessageNotReadableException(){
        super("Unable to read request data");
    }
}
