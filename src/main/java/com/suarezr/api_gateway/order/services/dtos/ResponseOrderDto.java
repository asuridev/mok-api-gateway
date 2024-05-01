package com.suarezr.api_gateway.order.services.dtos;


import com.suarezr.api_gateway.orderItem.services.dtos.ResponseOrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseOrderDto {
  private String id;

  private String owner;

  private String address;

  private String state;

  List<ResponseOrderItemDto> orderItem;
}

  