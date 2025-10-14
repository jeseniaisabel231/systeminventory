package com.example.systeminventory.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.systeminventory.dto.CategoryDTO;
import com.example.systeminventory.models.Category;
import com.example.systeminventory.repositories.CategoryRepository;

public class CategoryService {
  @Autowired
  private CategoryRepository repository;

  @Autowired
  private ModelMapper modelmapper;

  public Optional<Category> createCategory(CategoryDTO categoryDTO) {
    String code = categoryDTO.getCode();
    if (repository.existsByCode(code))
      throw new RuntimeException("Ya existe un producto con el codigo " + code);
      
      String nameDTO = categoryDTO.getName();
    if (repository.existsByName(nameDTO))
      throw new RuntimeException("Ya existe un registro con ese nombre");

    Category categoryDB = modelmapper.map(categoryDTO, Category.class);
      
    return Optional.of(repository.save(categoryDB));
  }

  public Optional<Category> getCategoryById(Long id) {
    return repository.findById(id);
  }

  public List<Category> getAllCategories() {
    return repository.findAll();
  }

  public Optional<Object> updateCategory(Long id, CategoryDTO categoryDTO) {
    Category categoryDB = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe la categoria con el id " + id));

    String codeDTO = categoryDTO.getCode();
    if (!codeDTO.equals(categoryDB.getCode()) && repository.existsByCode(codeDTO))
      throw new RuntimeException("Ya existe un registro con ese código");

    categoryDB.setCode(codeDTO);

    String nameDTO = categoryDTO.getName();
    if (!nameDTO.equals(categoryDB.getCode()) && repository.existsByName(nameDTO))
      throw new RuntimeException("Ya existe un registro con ese nombre");

    categoryDB.setName(nameDTO);
    categoryDB.setDescription(categoryDTO.getDescription());
    return Optional.of(repository.save(categoryDB));
  }

  public void deleteCategory(Long id) {
    repository.deleteById(id);
  }
}
