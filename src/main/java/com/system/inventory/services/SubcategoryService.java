package com.system.inventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.inventory.dto.SubcategoryDTO;
import com.system.inventory.mapper.SubcategoryMapper;
import com.system.inventory.models.Category;
import com.system.inventory.models.Subcategory;
import com.system.inventory.repositories.CategoryRepository;
import com.system.inventory.repositories.SubcategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubcategoryService {
  // Los repositorios son una conexion a la tabla en la base datos y se hacen
  // consultan tanto comunes (CRUD) como personalizadas

  private final SubcategoryRepository repository;

  private final CategoryRepository categoryRepository;

  private final SubcategoryMapper subcategoryMapper;

  public Optional<Subcategory> createSubcategory(final SubcategoryDTO subcategoryDTO) {
    final String codeDTO = subcategoryDTO.getCode();

    if (repository.existsByCode(codeDTO))
      throw new RuntimeException("Ya existe un producto con el codigo " + codeDTO);

    final String nameDTO = subcategoryDTO.getName();

    if (repository.existsByName(nameDTO))
      throw new RuntimeException("Ya existe un producto con el nombre " + nameDTO);

    // Obtener la categoria asociada usando el categoryId del DTO
    final Long categoryIdDTO = subcategoryDTO.getCategoryId();

    // Buscar la categoria en la base de datos
    final Category category = categoryRepository.findById(categoryIdDTO)
        .orElseThrow(() -> new RuntimeException("No existe la categoria con el id " + categoryIdDTO));

    subcategoryDTO.setCategoryId(null); // Nos aseguramos que el id sea nulo para que se genere uno nuevo

    final Subcategory subcategory = subcategoryMapper.toEntity(subcategoryDTO);

    subcategory.setCategory(category);

    return Optional.of(repository.save(subcategory)); // Guarda el objeto en la base de datos y lo retorna
  }

  @Transactional(readOnly = true)
  public Optional<Subcategory> getSubcategoryById(final Long id) {
    final Subcategory subcategory = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe la subcategoria con id " + id));

    return Optional.of(subcategory);
  }

  public List<Subcategory> getAllSubcategory() {
    return repository.findAll();
  }

  @Transactional
  public void deleteSubcategory(final Long id) {
    repository.deleteById(id);
  }

  @Transactional
  public Optional<Object> updateSubcategory(final Long id, final SubcategoryDTO subcategoryDTO) {
    // Si encuentra -> Se continua el flujo, si no -> Lanza una excepcion

    final Subcategory subcategoryDB = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe la subcategoria con el id " + id));

    // Validacion del codigo

    final String codeDTO = subcategoryDTO.getCode(); // De los datos enviados en el formulario, extraemos el codigo

    if (!codeDTO.equals(subcategoryDB.getCode()) && repository.existsByCode(codeDTO))
      throw new RuntimeException("Ya existe un registro con ese código");

    subcategoryDB.setCode(codeDTO);

    // Validacion del nombre

    final String nameDTO = subcategoryDTO.getName(); // De los datos enviados en el formulario, extraemos el nombre

    if (!nameDTO.equals(subcategoryDB.getName()) && repository.existsByName(nameDTO))
      throw new RuntimeException("Ya existe un registro con ese nombre de subcategoria");

    subcategoryDB.setName(nameDTO);

    // En la descripcion no se necesita una validacion de negocio

    subcategoryDB.setDescription(subcategoryDTO.getDescription());

    final Long categoryIdDTO = subcategoryDTO.getCategoryId();

    if (categoryIdDTO != subcategoryDB.getCategory().getId()) {
      final Category category = categoryRepository.findById(categoryIdDTO)
          .orElseThrow(() -> new RuntimeException("No existe la categoria con el id " + categoryIdDTO));
      subcategoryDB.setCategory(category);
    }

    return Optional.of(repository.save(subcategoryDB));
  }

  @Transactional(readOnly = true)
  public List<Subcategory> searchSubcategorys(final String subcatName) {
    return repository.findByNameContainingIgnoreCase(subcatName);
  }
}
