package com.example.systeminventory.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class subcategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable =  false, unique = true, length = 7)
  private String codePdt;

  @Column(nullable = false, length = 100)
  private String nombre;

  @Column(nullable = false)
  private String description;
  
  @Column(nullable = false)
  private Boolean estado = true;


}
