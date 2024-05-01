package com.suarezr.api_gateway.order.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderEntity {


    private String id;

    private String owner;

    private String address;

    private String state;

    List<OrderItemEntity> orderItem;
}

  