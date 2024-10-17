package com.cw.littlefins_proj.exception;

public class ResourceNotFoundException extends RuntimeException {
    //constructor
    public ResourceNotFoundException(){
        super("Resource not found");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
