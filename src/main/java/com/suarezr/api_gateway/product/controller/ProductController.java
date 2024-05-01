package com.suarezr.api_gateway.product.controller;


import com.suarezr.api_gateway.common.dtos.PaginationDto;
import com.suarezr.api_gateway.common.dtos.PathParamDto;
import com.suarezr.api_gateway.common.nats.NatsClient;
import com.suarezr.api_gateway.product.services.dtos.CreateProductDto;
import com.suarezr.api_gateway.product.services.dtos.ResponseProductDto;
import com.suarezr.api_gateway.product.services.dtos.UpdateProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

  private final NatsClient natsClient;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseProductDto create(@Valid @RequestBody CreateProductDto createProductDto){
    return this.natsClient.request("product.create", createProductDto, ResponseProductDto.class);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<ResponseProductDto> findAll(
          @RequestParam(required = false, defaultValue = "1") int page,
          @RequestParam(required = false,defaultValue = "10") int limit,
          @RequestParam(required = false,defaultValue = "id") String sortBy
  ){
    PaginationDto paginationDto = new PaginationDto(page - 1, limit, sortBy);
    return this.natsClient.request("product.read", paginationDto, List.class);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  public ResponseProductDto findOne(@PathVariable("id") String id){
    PathParamDto pathParamDto = new PathParamDto(id);
    return this.natsClient.request("product.read_by_id", pathParamDto, ResponseProductDto.class);
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping("/{id}")
  public ResponseProductDto update(@Valid @RequestBody UpdateProductDto updateProductDto, @PathVariable("id") String id){
    updateProductDto.setId(id);
    return this.natsClient.request("product.update", updateProductDto, ResponseProductDto.class);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void remove(@PathVariable("id") String id){
    PathParamDto pathParamDto = new PathParamDto(id);
    this.natsClient.request("product.delete", pathParamDto, String.class);
  }


}
