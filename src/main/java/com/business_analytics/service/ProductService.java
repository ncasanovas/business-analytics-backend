package com.business_analytics.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business_analytics.model.dto.ProductDTO;
import com.business_analytics.model.entity.Product;
import com.business_analytics.model.mapper.ProductMapper;
import com.business_analytics.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private final ProductRepository productRepository;

  @Autowired
  private ProductMapper productMapper;

  public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  public List<ProductDTO> getAllProducts() {
    return productRepository.findAll()
        .stream()
        .map(productMapper::toDto)
        .toList();
  }

  public Optional<ProductDTO> getProduct(Long id) {
    return productRepository.findById(id)
        .map(productMapper::toDto);
  }

  public ProductDTO createProduct(ProductDTO productDTO) {
    Product product = productMapper.toEntity(productDTO);
    Product savedProduct = productRepository.save(product);
    return productMapper.toDto(savedProduct);
  }

}
