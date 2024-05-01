package com.suarezr.api_gateway.common.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(){

    }
    public BadRequestException(String message) {
        super(message);
    }
}
  