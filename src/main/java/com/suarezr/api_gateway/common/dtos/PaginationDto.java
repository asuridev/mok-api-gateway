package com.suarezr.api_gateway.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationDto {
  private Integer page;
  private Integer limit;
  private String sortBy;
}
