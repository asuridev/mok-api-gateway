package com.suarezr.api_gateway.category.controller;

import com.suarezr.api_gateway.category.services.dtos.CreateCategoryDto;
import com.suarezr.api_gateway.category.services.dtos.ResponseCategoryDto;
import com.suarezr.api_gateway.category.services.dtos.UpdateCategoryDto;
import com.suarezr.api_gateway.common.dtos.PaginationDto;
import com.suarezr.api_gateway.common.dtos.PathParamDto;
import com.suarezr.api_gateway.common.nats.NatsClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("category")
public class CategoryController {

  private final NatsClient natsClient;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseCategoryDto create(@Valid @RequestBody CreateCategoryDto createCategoryDto){
    return this.natsClient.request("category.create", createCategoryDto, ResponseCategoryDto.class);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<ResponseCategoryDto> findAll(
          @RequestParam(required = false, defaultValue = "1") int page,
          @RequestParam(required = false,defaultValue = "10") int limit,
          @RequestParam(required = false,defaultValue = "id") String sortBy
  ){
    PaginationDto paginationDto = new PaginationDto(page - 1, limit, sortBy);
     return this.natsClient.request("category.read", paginationDto, List.class);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  public ResponseCategoryDto findOne(@PathVariable("id") String id){
    PathParamDto pathParamDto = new PathParamDto(id);
    return this.natsClient.request("category.read_by_id", pathParamDto, ResponseCategoryDto.class);
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping("/{id}")
  public ResponseCategoryDto update(@Valid @RequestBody UpdateCategoryDto updateCategoryDto, @PathVariable("id") String id){
    UpdateCategoryDto update = new UpdateCategoryDto(id, updateCategoryDto.getName());
    return this.natsClient.request("category.update", update, ResponseCategoryDto.class);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void remove(@PathVariable("id") String id){
    PathParamDto pathParamDto = new PathParamDto(id);
     this.natsClient.request("category.delete", pathParamDto, String.class);
  }

}
