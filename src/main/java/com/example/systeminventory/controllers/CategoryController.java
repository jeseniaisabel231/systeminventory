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

import com.example.systeminventory.dto.CategoryDTO;
import com.example.systeminventory.models.Category;
import com.example.systeminventory.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public List<Category> getAllCategorys() {
		return categoryService.getAllCategories();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getCategoryById(@PathVariable Long id) {
		try {
			Optional<Category> category = categoryService.getCategoryById(id);
			return ResponseEntity.ok(category);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", "No se encontró una categoria con el id " + id));
		}
	}

	@PostMapping
	public ResponseEntity<Object> createCategory(@RequestBody CategoryDTO categoryDTO){
		try {
			Optional <Category> category = categoryService.createCategory(categoryDTO);
			return ResponseEntity.ok(category);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
		try {
			Optional<Object> category = categoryService.updateCategory(id, categoryDTO);
			return ResponseEntity.ok(category);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable Long id){
		try {
			categoryService.deleteCategory(id);
			return ResponseEntity.ok(Map.of("response", "Producto con id " + id + " eliminado exitosamente"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

}
