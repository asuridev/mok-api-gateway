package com.suarezr.api_gateway.orderItem.services.dtos;



import com.suarezr.api_gateway.order.services.dtos.UpdateOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderItemDto {

  private String id;

  private String productId;

  private Integer quantity;

  private UpdateOrderDto order;
}

  