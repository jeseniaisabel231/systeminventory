package com.example.systeminventory.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class product {

  @Id // Identificador del producto
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false, unique = true, length = 7)
  private String code;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false)
  private Double price;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Integer stock;

}
