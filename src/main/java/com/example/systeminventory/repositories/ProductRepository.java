package com.example.systeminventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.systeminventory.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByCode(String code);

	Boolean existsByCode(String code);

	List<Product> findByTrademark(String trademark);
	
}
