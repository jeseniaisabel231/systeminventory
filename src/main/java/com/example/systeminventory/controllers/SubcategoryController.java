package com.example.systeminventory.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.systeminventory.dto.SubcategoryDTO;
import com.example.systeminventory.models.Subcategory;
import com.example.systeminventory.services.SubcategoryService;

@RestController
@RequestMapping("/subcategory")
public class SubcategoryController {
	@Autowired
	private SubcategoryService subcategoryService;

	@GetMapping
	public List <Subcategory> getAllSubcategories(){
		return subcategoryService.getAllSubcategory();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getSubcategoryById(@PathVariable Long id){
		try {
			Optional<Subcategory> subcategory = subcategoryService.getSubcategoryById(id);
			return ResponseEntity.ok(subcategory);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage())); 
		}
	}
	@PostMapping
	public ResponseEntity<Object> createSubcategory(@RequestBody SubcategoryDTO subcategoryDTO){
		try {
			Optional<Subcategory> subcategory = subcategoryService.createSubcategory(subcategoryDTO);
			return ResponseEntity.ok(subcategory);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateSubcategory(@PathVariable Long id, @RequestBody SubcategoryDTO subcategoryDTO ){
		try {
			Optional<Object> subcategory = subcategoryService.updateSubcategory(id, subcategoryDTO);
			return ResponseEntity.ok(subcategory);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@DeleteMapping("/{id}") // Anotación para manejar solicitudes DELETE con un ID de producto
  public ResponseEntity<Object> deleteSubcategory(@PathVariable Long id) {
    try {
      subcategoryService.deleteSubcategory(id); // Llama al servicio para eliminar un producto por su ID
      return ResponseEntity.ok(Map.of("response", "Subcategoryo con id " + id + " eliminado exitosamente"));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
    }
  }
	
}
