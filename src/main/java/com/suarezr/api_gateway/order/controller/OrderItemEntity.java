package com.suarezr.api_gateway.order.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderItemEntity  {


    private String id;


    private String productId;

    private Integer quantity;

    private OrderEntity order;
}

  