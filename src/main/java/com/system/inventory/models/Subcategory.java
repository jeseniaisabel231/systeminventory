package com.system.inventory.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Para que cree los setters y getters
@AllArgsConstructor // Para que cree el constructor con todas las propiedades de la clase
@NoArgsConstructor // Para que cree el constructor vacio
@Entity
@Table(name = "subcategories")
public class Subcategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 7)
  private String code;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false)
  private String description;

  // Relaciones

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false)
  @JsonBackReference
  private Category category;

  @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Product> products = new ArrayList<>();
}
