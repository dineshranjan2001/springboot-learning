package com.springboot.pratice.exception;

public class ResourceExistsException extends  RuntimeException{
    public ResourceExistsException(String message){
        super(message);
    }
}
