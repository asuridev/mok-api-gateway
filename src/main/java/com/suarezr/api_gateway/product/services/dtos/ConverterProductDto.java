package com.suarezr.api_gateway.product.services.dtos;



import com.suarezr.api_gateway.category.services.dtos.ConverterCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ConverterCategoryDto.class})
public interface ConverterProductDto {

    @Mappings({

    })
    ResponseProductDto toResponseProductDto(ProductDto productDto);

    List<ResponseProductDto> toResponseProductDtoList(List<ProductDto> productDtoList);
}
  