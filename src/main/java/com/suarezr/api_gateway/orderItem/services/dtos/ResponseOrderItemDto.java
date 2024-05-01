package com.suarezr.api_gateway.orderItem.services.dtos;


import com.suarezr.api_gateway.order.services.dtos.ResponseOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseOrderItemDto {
  private String id;

  private String productId;

  private Integer quantity;

  private ResponseOrderDto order;
}

  