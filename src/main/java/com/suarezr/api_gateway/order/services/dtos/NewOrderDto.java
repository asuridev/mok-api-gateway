package com.suarezr.api_gateway.order.services.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewOrderDto {
  private String id;

  private String owner;

  private String address;

  private String state;

  private List<ItemDto> items;

}
