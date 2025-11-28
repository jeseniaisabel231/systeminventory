package com.system.inventory.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.inventory.dto.SubcategoryDTO;
import com.system.inventory.models.Subcategory;
import com.system.inventory.services.SubcategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/subcategory")
public class SubcategoryController {
	@Autowired
	private SubcategoryService subcategoryService;

	@GetMapping
	public List<Subcategory> getAllSubcategories() {
		return subcategoryService.getAllSubcategory();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getSubcategoryById(@PathVariable final Long id) {
		try {
			final Optional<Subcategory> subcategory = subcategoryService.getSubcategoryById(id);
			return ResponseEntity.ok(subcategory);
		} catch (final Exception e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@PostMapping
	public ResponseEntity<Object> createSubcategory(@Valid @RequestBody final SubcategoryDTO subcategoryDTO) {
		try {
			final Optional<Subcategory> subcategory = subcategoryService.createSubcategory(subcategoryDTO);
			return ResponseEntity.status(201).body(subcategory);
		} catch (final RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateSubcategory(@PathVariable final Long id,
			@Valid @RequestBody final SubcategoryDTO subcategoryDTO) {
		try {
			final Optional<Object> subcategory = subcategoryService.updateSubcategory(id, subcategoryDTO);
			return ResponseEntity.ok(subcategory);
		} catch (final RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@DeleteMapping("/{id}") // Anotación para manejar solicitudes DELETE con un ID de producto
	public ResponseEntity<Object> deleteSubcategory(@PathVariable final Long id) {
		try {
			subcategoryService.deleteSubcategory(id); // Llama al servicio para eliminar un producto por su ID
			return ResponseEntity.ok(Map.of("response", "Subcategoryo con id " + id + " eliminado exitosamente"));
		} catch (final RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@GetMapping("/search")
	public ResponseEntity<Object> searchSubcategory(@RequestParam final String subcatName){
		try {
			final List<Subcategory> searchedSubcategorys = subcategoryService.searchSubcategorys(subcatName);
			return ResponseEntity.ok(searchedSubcategorys);
		} catch (final RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
