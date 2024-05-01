package com.suarezr.api_gateway.order.services.dtos;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConverterOrderDto {

    @Mappings({

    })
    ResponseOrderDto toResponseOrderDto(OrderDto orderDto);

    List<ResponseOrderDto> toResponseOrderDtoList(List<OrderDto> orderDtoList);
}
  