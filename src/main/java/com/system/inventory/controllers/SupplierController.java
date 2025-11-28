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

import com.system.inventory.dto.SupplierDTO;
import com.system.inventory.models.Supplier;
import com.system.inventory.services.SupplierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping
	public List<Supplier> getAllSuppliers(){
		return supplierService.getAllSuppliers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getSupplierById(@PathVariable final Long id){
		try {
			final Optional<Supplier> supplier = supplierService.getSupplierById(id);

			return ResponseEntity.ok(supplier);	
		} catch (final RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@PostMapping
	public ResponseEntity<Object> createSupplier(@Valid @RequestBody final SupplierDTO supplierDTO){
		try {
			final Optional<Supplier> supplier = supplierService.createSupplier(supplierDTO);
			return ResponseEntity.status(201).body(supplier);
		} catch (final RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateSupplier(@PathVariable final Long id, @Valid @RequestBody final SupplierDTO supplierDTO){
		try {
			final Optional<Object> supplier = supplierService.updateSupplier(id, supplierDTO);
			return ResponseEntity.ok(supplier);
		} catch (final RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteSupplier(@PathVariable final Long id){
		try {
			supplierService.deleteSupplier(id);
			return ResponseEntity.ok(Map.of("response", "Proveedor con id" + id + "eliminado existosamente"));
		} catch (final RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("response", e .getMessage()));
		}
	}

	@GetMapping("/search")
	public ResponseEntity<Object> searchSupplier(@RequestParam final String supplName){
		try {
			final List<Supplier> searchedSuppliers = supplierService.searchSuppliers(supplName);
			return ResponseEntity.ok(searchedSuppliers);
		} catch (final RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
