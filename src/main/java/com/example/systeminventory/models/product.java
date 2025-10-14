package com.example.systeminventory.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data // Para que cree los setters y getters
@AllArgsConstructor // Para que cree el constructor con todas las propiedades de la clase
@NoArgsConstructor // Para que cree el constructor vacio
public class Product {
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

  @Column(nullable = false)
  private String trademark;
}
