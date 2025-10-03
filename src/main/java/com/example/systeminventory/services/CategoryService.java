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

  public Optional<Object> createCategory(CategoryDTO categoryDTO) {
    String code = categoryDTO.getCode();
    if (repository.existsByCode(code))
      throw new RuntimeException("Ya existe un producto con el codigo " + code);
    Category category = modelmapper.map(categoryDTO, Category.class);
    return Optional.of(repository.save(category));
  }
  public Optional<Category> getCategory(Long id){
    return repository.findById(id);
  }
  public List<Category> getAllCategories(){
    return repository.findAll();
  }
  public void deleteCategory(Long id){
    repository.deleteById(id);
  }
}
