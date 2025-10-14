package com.example.systeminventory.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.systeminventory.dto.SubcategoryDTO;
import com.example.systeminventory.models.Subcategory;
import com.example.systeminventory.repositories.SubcategoryRepository;

public class SubcategoryService {
  @Autowired
  private SubcategoryRepository repository; // Los repositorios son una conexion a la tabla en la base datos y se hacen consultan tanto comunes (CRUD) como personalizadas

  @Autowired
  private ModelMapper modelmapper;

  public Optional<Subcategory> createSubcategory(SubcategoryDTO subcategoryDTO){
    String codeDTO = subcategoryDTO.getCode();
    
    if(repository.existsByCode(codeDTO))
      throw new RuntimeException("Ya existe un producto con el codigo " + codeDTO);

    String nameDTO = subcategoryDTO.getName();
    
    if(repository.existsByName(nameDTO))
      throw new RuntimeException("Ya existe un producto con el nombre " + nameDTO);
    
    Subcategory subcategory = modelmapper.map(subcategoryDTO, Subcategory.class);
    
    return Optional.of(repository.save(subcategory));
  }

  public Optional<Subcategory> getSubcategoryById(Long id){
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
    Subcategory subcategoryDB = repository.findById(id)
      .orElseThrow(() -> new RuntimeException("No existe la subcategoria con el id " + id)); // Si encuentra -> Se almacena el id, nombre, codigo y descripcion en la variable

    // Validacion del codigo

    String codeDTO = subcategoryDTO.getCode(); // De los datos enviados en el formulario, extraemos el codigo

    if (!codeDTO.equals(subcategoryDB.getCode()) && repository.existsByCode(codeDTO))
      throw new RuntimeException("Ya existe un registro con ese código");
    
    subcategoryDB.setCode(codeDTO);

    // Validacion del nombre

    String nameDTO = subcategoryDTO.getName(); // De los datos enviados en el formulario, extraemos el nombre

    if (!nameDTO.equals(subcategoryDB.getName()) && repository.existsByName(nameDTO))
      throw new RuntimeException("Ya existe un registro con ese nombre de subcategoria");

    subcategoryDB.setName(nameDTO);

    // En la descripcion no se necesita una validacion de negocio

    subcategoryDB.setDescription(subcategoryDTO.getDescription());

    return Optional.of(repository.save(subcategoryDB));
  }
}
