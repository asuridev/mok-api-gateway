package com.suarezr.api_gateway.orderItem.services.dtos;




import com.suarezr.api_gateway.order.services.dtos.ConverterOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ConverterOrderDto.class})
public interface ConverterOrderItemDto {

    @Mappings({

    })
    ResponseOrderItemDto toResponseOrderItemDto(OrderItemDto orderItemDto);

    List<ResponseOrderItemDto> toResponseOrderDtoList(List<OrderItemDto> orderDtoList);
}
  