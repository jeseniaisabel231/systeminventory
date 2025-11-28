package com.system.inventory.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
	@Pattern(regexp = "^[A-Za-zñÑáéíóúÁÉÍÓÚ ]+$", message = "El nombre solo puede contener letras, espacios y caracteres especiales")
	@NotEmpty(message = "El nombre del proveedor es obligatorio")
	@Length(min = 3, max = 30, message = "El nombre del proveedor debe tener entre 3 a 30 caracteres")
	private String name;

	@Email(message = "El formato del correo electronico es invalido")
	@NotBlank(message = "El email es obligatorio")
	private String email;

	@NotBlank(message ="El telefono es obligatorio")
	@Pattern(regexp = "^09\\d{8}$", message = "El teléfono debe tener exactamente 10 dígitos y empezar con 09")
	private String contact;
}
