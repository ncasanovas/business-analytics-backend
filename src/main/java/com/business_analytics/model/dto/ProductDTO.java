package com.business_analytics.model.dto;

public class ProductDTO {

  private String name;
  private String category;
  private Double price;

  public ProductDTO(String name, String category, Double price) {
    this.name = name;
    this.category = category;
    this.price = price;
  }

  // Getters and Setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

}
