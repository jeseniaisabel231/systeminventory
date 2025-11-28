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

import com.system.inventory.dto.CategoryDTO;
import com.system.inventory.models.Category;
import com.system.inventory.services.CategoryService;

import jakarta.validation.Valid;

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
	public ResponseEntity<Object> getCategoryById(@PathVariable final Long id) {
		try {
			final Optional<Category> category = categoryService.getCategoryById(id);
			return ResponseEntity.ok(category);
		} catch (final RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@PostMapping
	public ResponseEntity<Object> createCategory(@Valid @RequestBody final CategoryDTO categoryDTO) {
		try {
			final Optional<Category> category = categoryService.createCategory(categoryDTO);
			return ResponseEntity.status(201).body(category);
		} catch (final RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable final Long id,
			@Valid @RequestBody final CategoryDTO categoryDTO) {
		try {
			final Optional<Object> category = categoryService.updateCategory(id, categoryDTO);
			return ResponseEntity.ok(category);
		} catch (final RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable final Long id) {
		try {
			categoryService.deleteCategory(id);
			return ResponseEntity.ok(Map.of("response", "Producto con id " + id + " eliminado exitosamente"));
		} catch (final Exception e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@GetMapping("/search")
	public ResponseEntity<Object> searchCategory(@RequestParam final String catName){
		try {
			final List<Category> searchedCategorys = categoryService.searchCategorys(catName);
			return ResponseEntity.ok(searchedCategorys);
		} catch (final RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
