package com.business_analytics.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.business_analytics.model.dto.ProductDTO;
import com.business_analytics.model.entity.Product;

@Component
public class ProductMapper {

  @Autowired
  private ModelMapper modelMapper;

  public ProductDTO toDto(Product user) {
    return modelMapper.map(user, ProductDTO.class);
  }

  public Product toEntity(ProductDTO dto) {
    return modelMapper.map(dto, Product.class);
  }

}
