package com.system.inventory.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Para que cree los setters y getters
@AllArgsConstructor // Para que cree el constructor con todas las propiedades de la clase
@NoArgsConstructor // Para que cree el constructor vacio
public class CategoryDTO {
  // Formato CAT-001
  @Pattern(regexp = "^CAT-\\d{3}$", message = "El codigo de la categoria debe cumplir con el formato establecido (CAT-###)")
  private String code;

  // Formato debe tener entre 3 y 30 caracteres alfabeticos
  @Length(min = 3, max = 30, message = "El nombre de la categoria debe tener entre 3 y 30 caracteres")
  @NotEmpty(message = "El nombre de la categoria no debe estar vacio")
  @Pattern(regexp = "^(?!\\s*$)[A-Za-zÁÉÍÓÚáéíóúÑñÜü\\s]+$", message = "El nombre de la categoria debe contener solo caracteres (no simbolos ni numeros)")
  private String name;

  // Formato debe tener entre 10 y 200 caracteres alfanumericos, minimo 8 de ellos deben ser alfabeticos
  @NotEmpty(message = "La descripcion de la categoria no debe estar vacia")
  @Length(min = 10, max = 200, message = "La descripcion de la categoria debe tener minimo 10 caracteres y maximo 200 caracteres")
  @Pattern(regexp = "^(?=(.*[A-Za-zñÑáéíóúÁÉÍÓÚ]){8}).{10,200}$", message = "La descripción debe contener al menos 8 letras")
  private String description;
}
