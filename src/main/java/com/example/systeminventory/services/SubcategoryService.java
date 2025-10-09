package com.example.systeminventory.services;

import java.util.List;
import java.util.Optional;
import java.util.Locale.Category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.systeminventory.dto.SubcategoryDTO;
import com.example.systeminventory.models.Subcategory;
import com.example.systeminventory.repositories.SubcategoryRepository;

public class SubcategoryService {
  @Autowired
  private SubcategoryRepository repository;

  @Autowired
  private ModelMapper modelmapper;

  public Optional<Object> createSubcategory(SubcategoryDTO subcategoryDTO){
    String code = subcategoryDTO.getCode();
    
    if(repository.existsByCode(code))
      throw new RuntimeException("Ya existe un producto con el codigo " + code);
    
    Subcategory subcategory = modelmapper.map(subcategoryDTO, Subcategory.class);
    
    return Optional.of(repository.save(subcategory));
  }

  public Optional<Subcategory> getSubcategory(Long id){
    Subcategory subcategory = repository.findById(id)
      .orElseThrow(() -> new RuntimeException("No existe la subcategoria con id " + id));

    return Optional.of(subcategory);
  }

  public List<Subcategory> getAllSubcategory(){
    return repository.findAll();
  }

  public void deleteSubcategory(Long id){
    repository.deleteById(id);
  }

  public Optional<Object> updateSubcategory(Long id, SubcategoryDTO subcategoryDTO) {
    Subcategory subcategory = repository.findById(id)
      .orElseThrow(() -> new RuntimeException("No existe la subcategoria con el id " + id));

    String code = subcategoryDTO.getCode();

    if (!code.equals(subcategory.getCode()) && repository.existsByCode(code))
      throw new RuntimeException("Ya existe un registro con ese código");
    
  }
}
