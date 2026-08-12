package com.business_analytics.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business_analytics.model.dto.ProductDTO;
import com.business_analytics.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getProducts() {

    return ResponseEntity.ok(productService.getAllProducts());

  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {

    return productService.getProduct(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

  }

  @PostMapping
  public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
    ProductDTO created = productService.createProduct(productDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);

  }

}
