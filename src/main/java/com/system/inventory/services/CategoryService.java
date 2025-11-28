package com.system.inventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.inventory.dto.CategoryDTO;
import com.system.inventory.mapper.CategoryMapper;
import com.system.inventory.models.Category;
import com.system.inventory.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository repository;

  private final CategoryMapper categoryMapper;

  @Transactional
  public Optional<Category> createCategory(CategoryDTO categoryDTO) {
    String code = categoryDTO.getCode();

    if (repository.existsByCode(code))
      throw new RuntimeException("Ya existe un producto con el codigo " + code);

    String nameDTO = categoryDTO.getName();
    if (repository.existsByName(nameDTO))
      throw new RuntimeException("Ya existe un registro con ese nombre");

    Category categoryDB = categoryMapper.toEntity(categoryDTO); // Modelo, Entity o DB son lo mismo

    return Optional.of(repository.save(categoryDB));
  }

  @Transactional(readOnly = true)
  public Optional<Category> getCategoryById(Long id) {
    Category categoryDB = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe la categoria con el id " + id));

    return Optional.of(categoryDB);
  }

  @Transactional(readOnly = true)
  public List<Category> getAllCategories() {
    return repository.findAll();
  }

  @Transactional
  public Optional<Object> updateCategory(Long id, CategoryDTO categoryDTO) {
    Category categoryDB = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe la categoria con el id " + id));

    String codeDTO = categoryDTO.getCode();
    if (!codeDTO.equals(categoryDB.getCode()) && repository.existsByCode(codeDTO))
      throw new RuntimeException("Ya existe un registro con ese código");

    categoryDB.setCode(codeDTO);

    String nameDTO = categoryDTO.getName();
    if (!nameDTO.equals(categoryDB.getName()) && repository.existsByName(nameDTO))
      throw new RuntimeException("Ya existe un registro con ese nombre");

    categoryDB.setName(nameDTO);
    categoryDB.setDescription(categoryDTO.getDescription());
    return Optional.of(repository.save(categoryDB));
  }

  @Transactional
  public void deleteCategory(Long id) {
    repository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<Category> searchCategorys(String catName){
    return repository.findByNameContainingIgnoreCase(catName);
  }
}
