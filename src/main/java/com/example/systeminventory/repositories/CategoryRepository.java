package com.example.systeminventory.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.systeminventory.models.Category;

// Se usa para establecer consultas SQL a la base de datos
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> { // El segundo parametro es el tipo de dato de su ID, en Category es Long
	// Optional es un tipo de dato que indica que la variable puede ser vacia
	Optional<Category> findByCode(String code); // Este retorna toda la información de Category

	Boolean existsByCode(String code); // Te retorna true si existe la Category con el código solicitado

	Boolean existsByName(String name); // Te retorna true si existe la Category con el código solicitado


}