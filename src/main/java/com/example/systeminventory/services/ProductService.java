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

    String nameDTO = productDTO.getName();
    if (repository.existsByName(nameDTO))
      throw new RuntimeException("Ya existe un registro con ese nombre");

    Product product = modelmapper.map(productDTO, Product.class); // Conversion de DTO a modelo de la base de datos

    // Retorno un objeto opcional
    return Optional.of(repository.save(product));
  }

  public Optional<Product> getProductById(Long id) {
    return repository.findById(id);
  }

  public List<Product> getAllProducts() {
    return repository.findAll();
  }

  public Optional<Object> updateProducts(Long id, ProductDTO productDTO) {
    Product productDB = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe la categoria con el id " + id));

    String codeDTO = productDTO.getCode();
    if (!codeDTO.equals(productDB.getCode()) && repository.existsByCode(codeDTO))
      throw new RuntimeException("Ya existe un registro con ese código");

    productDB.setCode(codeDTO);

    String nameDTO = productDTO.getName();
    if (!nameDTO.equals(productDB.getCode()) && repository.existsByName(nameDTO))
      throw new RuntimeException("Ya existe un registro con ese nombre");

    productDB.setName(nameDTO);
    productDB.setDescription(productDTO.getDescription());
    return Optional.of(repository.save(productDB));
  }

  public void deleteProduct(Long id) {
    repository.deleteById(id);
  }
}
