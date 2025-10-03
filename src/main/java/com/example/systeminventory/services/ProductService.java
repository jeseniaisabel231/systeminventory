package com.example.systeminventory.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.systeminventory.dto.ProductDTO;
import com.example.systeminventory.models.Product;
import com.example.systeminventory.repositories.ProductRepository;

public class ProductService {
  @Autowired // Inyecta un servicio, en esta caso el repositorio
  private ProductRepository repository;

  @Autowired
  private ModelMapper modelmapper;

  public Optional<Object> createProduct(ProductDTO productDTO) {
    // Validaciones
    String code = productDTO.getCode();

    if (repository.existsByCode(code))
      throw new RuntimeException("Ya existe un producto con el codigo " + code);

    Product product = modelmapper.map(productDTO, Product.class); // Conversion de DTO a modelo de la base de datos

    // Retorno un objeto opcional
    return Optional.of(repository.save(product));
  }

  public Optional<Product> getProduct(Long id) {
    return repository.findById(id);
  }

  public List<Product> getAllProducts() {
    return repository.findAll();
  }

  public void deleteProduct(Long id){
    repository.deleteById(id);
  }
}
