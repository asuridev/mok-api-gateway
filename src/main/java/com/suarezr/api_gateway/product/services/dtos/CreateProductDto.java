package com.suarezr.api_gateway.product.services.dtos;

import com.suarezr.api_gateway.category.services.dtos.CreateCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductDto {
  private String id;

  private String name;

  private String description;

  private Integer stock;

  private BigDecimal price;

  private CreateCategoryDto category;
     
}

  