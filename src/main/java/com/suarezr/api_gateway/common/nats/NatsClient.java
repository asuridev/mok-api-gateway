package com.suarezr.api_gateway.common.nats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suarezr.api_gateway.common.exceptions.BadRequestException;
import com.suarezr.api_gateway.common.exceptions.InternalServerErrorException;
import com.suarezr.api_gateway.common.exceptions.NotFoundException;
import com.suarezr.api_gateway.common.exceptions.UnauthorizedException;
import io.nats.client.Connection;
import io.nats.client.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.concurrent.*;

@Component
@RequiredArgsConstructor
public class NatsClient {

  private final Connection connection;

  public <T> T request(String pattern, Object payload, Class<T> valueType )  {
    try{
      String json = new ObjectMapper().writeValueAsString(payload);
      CompletableFuture<Message> future = this.connection.request(pattern, json.getBytes());
      Message msg = future.get(5, TimeUnit.SECONDS);
      String dataAsJson = new String(msg.getData());
      NatsResponseDto data = new ObjectMapper().readValue(dataAsJson, NatsResponseDto.class);
      if(data.success()) {
        return new ObjectMapper().readValue(data.payload(), valueType);
      }
      throwRpcException(data.statusCode(), data.errorMessage());
    }catch(ExecutionException | InterruptedException | TimeoutException | JsonProcessingException e){
      throw  new InternalServerErrorException(e.getMessage());
    }catch (CancellationException e){
      throw  new InternalServerErrorException("remote service " + pattern + " not found");
    }
    return null;
  }

  public String request(String pattern, Object payload )  {
    try{
      String json = new ObjectMapper().writeValueAsString(payload);
      CompletableFuture<Message> future = this.connection.request(pattern, json.getBytes());
      Message msg = future.get(1, TimeUnit.SECONDS);
      String dataAsJson = new String(msg.getData());
      NatsResponseDto data = new ObjectMapper().readValue(dataAsJson, NatsResponseDto.class);
      if(data.success()) return data.payload();
      throwRpcException(data.statusCode(), data.errorMessage());
    }catch( ExecutionException | InterruptedException | TimeoutException | JsonProcessingException e){
      throw  new InternalServerErrorException(e.getCause().toString());
    }catch (CancellationException e){
      throw  new InternalServerErrorException("remote service " + pattern + " not found");
    }
    return null;
  }

  private void throwRpcException(int statusCode, String errorMessage){
    switch (statusCode){
      case 401:
        throw new UnauthorizedException(errorMessage);
      case  404:
        throw new NotFoundException(errorMessage);
      case 400:
        throw new BadRequestException(errorMessage);
      default:
        throw new InternalServerErrorException(errorMessage);
    }
  }

  public <T> T toJava(String json, Class<T> valueType ){
    try {
      return new ObjectMapper().readValue(json, valueType);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public <T> T toJava(Message msg, Class<T> valueType ){
    String json = new String(msg.getData());
    try {
      return new ObjectMapper().readValue(json, valueType);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}
