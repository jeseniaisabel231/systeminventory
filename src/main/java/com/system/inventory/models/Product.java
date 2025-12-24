package com.system.inventory.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Para que cree los setters y getters
@AllArgsConstructor // Para que cree el constructor con todas las propiedades de la clase
@NoArgsConstructor // Para que cree el constructor vacio
@Entity // Indica que es una entidad de la base de datos
@Table(name = "products")
public class Product {
  @Id // Identificador del producto
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
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

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false)
  @JsonBackReference
  private Subcategory subcategory;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false)
  @JsonBackReference
  private Supplier supplier;
}
