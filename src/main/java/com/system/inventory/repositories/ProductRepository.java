package com.system.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.inventory.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByCode(String code);

	Boolean existsByCode(String code);

	List<Product> findByTrademark(String trademark);

	Boolean existsByName(String name);

	List<Product> findByNameContainingIgnoreCase(String name);

	// List<Product> findBySubcategoryId(Long subcategoryId);

	// List<Product> findBySupplierId(Long supplierId);
}
