package com.system.inventory.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Para que cree los setters y getters
@AllArgsConstructor // Para que cree el constructor con todas las propiedades de la clase
@NoArgsConstructor // Para que cree el constructor vacio
public class ProductDTO {
  @Pattern(regexp = "^PROD-\\d{4}$", message = "El codigo del producto debe cumplir con el formato establecido (PROD-####)")
  private String code;

  @NotBlank(message = "El nombre del producto es obligatorio")
  @Length(min = 3, max = 50, message = "El nombre del producto debe tener entre 3 y 50 caracteres")
  @Pattern(regexp = "^[A-Za-zñÑáéíóúÁÉÍÓÚ ]+$", message = "El nombre del producto solo puede contener letras, espacios y caracteres especiales")
  private String name;

  @NotNull(message = "El precio del producto es obligatorio")
  @Positive(message = "El precio del producto debe ser un numero mayor a 0")
  private Double price;

  @NotBlank(message = "La descripcion del producto es obligatoria")
  @Length(min = 10, max = 200, message = "La descripcion del producto debe tener entre 10 y 200 caracteres")
  @Pattern(regexp = "^(?=(.*[A-Za-zñÑáéíóúÁÉÍÓÚ]){8}).{10,200}$", message = "La descripción debe contener al menos 8 letras")
  private String description;

  @NotNull(message = "El stock del producto es obligatorio")
  @PositiveOrZero(message = "El stock del producto no puede ser un numero negativo")
  private Integer stock;

  @NotBlank(message = "La marca del producto es obligatoria")
  @Length(min = 2, max = 30, message = "La marca del producto debe tener entre 2 y 30 caracteres")
  private String trademark;

  @Positive(message = "El Id de la subcategoria debe ser un numero mayor a 0")
  @NotNull(message = "El Id de la subcategoria es obligatorio")
  private Long subcategoryId;
  
  @Positive(message = "El Id del proveedor debe ser un numero mayor a 0")
  @NotNull(message = "El Id del proveedor es obligatorio")
  private Long supplierId;
}
