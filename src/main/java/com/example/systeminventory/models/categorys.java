package com.example.systeminventory.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data // Para que cree los setters y getters
@AllArgsConstructor // Para que cree el constructor con todas las propiedades de la clase
@NoArgsConstructor // Para que cree el constructor vacio
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 7)
  private String code;

  @Column(nullable = false, length = 100)                                                 
  private String name;

  @Column(nullable = false)
  private String description;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Subcategory> subcategories = new ArrayList<>();
}
