package com.system.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.inventory.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	Boolean existsByName(String name);

	Boolean existsByEmail(String email);

	Boolean existsByContact(String contact);

	List<Supplier> findByNameContainingIgnoreCase(String name);
}