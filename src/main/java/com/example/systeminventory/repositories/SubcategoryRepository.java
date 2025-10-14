package com.example.systeminventory.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.systeminventory.models.Subcategory;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
	Optional<Subcategory> findbyCode(String code);

	Boolean existsByCode(String code);
	Boolean existsByName(String name);
}
