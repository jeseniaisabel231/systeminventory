package com.example.systeminventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Para que cree los setters y getters
@AllArgsConstructor // Para que cree el constructor con todas las propiedades de la clase
@NoArgsConstructor // Para que cree el constructor vacio
public class CategoryDTO {
  private String code;
  private String name;
  private String description;
}
