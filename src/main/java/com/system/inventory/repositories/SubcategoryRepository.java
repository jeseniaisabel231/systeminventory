package com.system.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.inventory.models.Subcategory;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
	Optional<Subcategory> findByCode(String code);

	Boolean existsByCode(String code);

	Boolean existsByName(String name);

	List<Subcategory> findByNameContainingIgnoreCase(String name);
}
