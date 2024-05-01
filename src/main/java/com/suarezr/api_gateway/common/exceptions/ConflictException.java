package com.suarezr.api_gateway.common.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(){

    }
    public ConflictException(String message) {
        super(message);
    }
}
  