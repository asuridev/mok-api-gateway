package com.suarezr.api_gateway.common.exceptions;

public class InternalServerErrorException extends RuntimeException {
  public InternalServerErrorException(){

  }
  public InternalServerErrorException(String message) {
    super(message);
  }
}
