package com.system.inventory.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Para que cree los setters y getters
@AllArgsConstructor // Para que cree el constructor con todas las propiedades de la clase
@NoArgsConstructor // Para que cree el constructor vacio
public class SubcategoryDTO {
  @Pattern(regexp = "^SUB-\\d{3}$", message = "El codigo de la subcategoria debe cumplir con el formato establecido (SUB-###)")
  private String code;

  @Length(min = 3, max= 30, message="El nombre de la subcategoria debe tener entre 3 y 30 caracteres")
  @NotEmpty(message="El nombre de la subcategoria es obligatorio")
  @Pattern(regexp = "^(?!\\s*$)[A-Za-zÁÉÍÓÚáéíóúÑñÜü\\s]+$", message = "El nombre de la subcategoria debe contener solo caracteres (no simbolos ni numeros)")
  private String name;

  @NotEmpty(message = "La descripcion de la subcategoria es obligatorio")
  @Length(message = "La descripcion de la categoria debe tener minimo 10 caracteres y maximo 200 caracteres")
  @Pattern(regexp = "^(?=(.*[A-Za-zñÑáéíóúÁÉÍÓÚ]){8}).{10,200}$", message = "La descripción debe contener al menos 8 letras")
  private String description;

  @Positive(message = "El Id de la categoria debe ser un numero mayor a 0")
  @NotNull
  private Long categoryId;
}
