package com.example.systeminventory.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Subcategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable =  false, unique = true, length = 7)
  private String code;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false)
  private String description;

  @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Product> products = new ArrayList<>();
}
