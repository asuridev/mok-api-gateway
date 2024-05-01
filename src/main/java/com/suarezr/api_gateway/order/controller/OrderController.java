package com.suarezr.api_gateway.order.controller;


import com.suarezr.api_gateway.category.services.dtos.ResponseCategoryDto;
import com.suarezr.api_gateway.category.services.dtos.UpdateCategoryDto;
import com.suarezr.api_gateway.common.dtos.PaginationDto;
import com.suarezr.api_gateway.common.dtos.PathParamDto;
import com.suarezr.api_gateway.common.nats.NatsClient;
import com.suarezr.api_gateway.order.services.dtos.NewOrderDto;
import com.suarezr.api_gateway.order.services.dtos.ResponseOrderDto;
import com.suarezr.api_gateway.order.services.dtos.UpdateOrderDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("order")
public class OrderController {

  private final NatsClient natsClient;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseOrderDto create(@Valid @RequestBody NewOrderDto newOrderDto){
    return this.natsClient.request("order.create", newOrderDto, ResponseOrderDto.class);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<OrderEntity> findAll(
          @RequestParam(required = false, defaultValue = "1") int page,
          @RequestParam(required = false,defaultValue = "10") int limit,
          @RequestParam(required = false,defaultValue = "id") String sortBy
  ){
    PaginationDto paginationDto = new PaginationDto(page - 1, limit, sortBy);
    return this.natsClient.request("order.read", paginationDto, List.class);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  public OrderEntity findOne(@PathVariable("id") String id){
    PathParamDto pathParamDto = new PathParamDto(id);
    return this.natsClient.request("order.read_by_id", pathParamDto, OrderEntity.class);
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping("/{id}")
  public OrderEntity update(@Valid @RequestBody UpdateOrderDto updateOrderDto, @PathVariable("id") String id){
    updateOrderDto.setId(id);
    return this.natsClient.request("order.update", updateOrderDto, OrderEntity.class);
  }

}
