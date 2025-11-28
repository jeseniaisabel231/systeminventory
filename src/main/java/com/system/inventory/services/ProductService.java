package com.system.inventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.inventory.dto.ProductDTO;
import com.system.inventory.mapper.ProductMapper;
import com.system.inventory.models.Product;
import com.system.inventory.models.Subcategory;
import com.system.inventory.models.Supplier;
import com.system.inventory.repositories.ProductRepository;
import com.system.inventory.repositories.SubcategoryRepository;
import com.system.inventory.repositories.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository repository;

  private final SubcategoryRepository subcategoryRepository;

  private final SupplierRepository supplierRepository;

  private final ProductMapper productMapper;

  @Transactional
  public Optional<Product> createProduct(final ProductDTO productDTO) {
    // Validaciones usando la logica de negocio
    final Double priceDTO = productDTO.getPrice();
    if (priceDTO < 0)
      throw new RuntimeException("El precio debe ser un numero positivo");

    // Validaciones usando la base de datos1
    final String code = productDTO.getCode();

    if (repository.existsByCode(code))
      throw new RuntimeException("Ya existe un producto con el codigo " + code);

    final String nameDTO = productDTO.getName();
    if (repository.existsByName(nameDTO))
      throw new RuntimeException("Ya existe un registro con ese nombre");

    final Long subcategoryIdDTO = productDTO.getSubcategoryId();

    final Subcategory subcategory = subcategoryRepository.findById(subcategoryIdDTO).orElseThrow(()-> new RuntimeException("No existe la subcategoria con el id " + subcategoryIdDTO));

    productDTO.setSubcategoryId(null);

    final Long supplierIdDTO = productDTO.getSupplierId();

    final Supplier supplier = supplierRepository.findById(supplierIdDTO).orElseThrow(()->new RuntimeException("No existe el proveedor con el id "+ supplierIdDTO));

    productDTO.setSupplierId(null);

    final Product product = productMapper.toEntity(productDTO);

    product.setSubcategory(subcategory);
    product.setSupplier(supplier);

    // Retorno un objeto opcional
    return Optional.of(repository.save(product));
  }

  @Transactional(readOnly = true)
  public Optional<Product> getProductById(final Long id) {
    return repository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Product> getAllProducts() {
    return repository.findAll();
  }

  @Transactional
  public Optional<Object> updateProducts(final Long id, final ProductDTO productDTO) {
    final Product productDB = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe la categoria con el id " + id));

    final Long subcategoryIdDTO = productDTO.getSubcategoryId();

    if (subcategoryIdDTO != productDB.getSubcategory().getId() && !subcategoryRepository.existsById(subcategoryIdDTO))
      throw new RuntimeException("No existe una subcategoria con el id " + subcategoryIdDTO);

    final Long supplierIdDTO = productDTO.getSupplierId();

    if (supplierIdDTO != productDB.getSupplier().getId() && !supplierRepository.existsById(supplierIdDTO))
      throw new RuntimeException("No existe un proveedor con el id " + supplierIdDTO);

    final String codeDTO = productDTO.getCode();
    if (!codeDTO.equals(productDB.getCode()) && repository.existsByCode(codeDTO))
      throw new RuntimeException("Ya existe un registro con ese código");

    productDB.setCode(codeDTO);

    final String nameDTO = productDTO.getName();
    if (!nameDTO.equals(productDB.getCode()) && repository.existsByName(nameDTO))
      throw new RuntimeException("Ya existe un registro con ese nombre");

    productDB.setName(nameDTO);
    productDB.setDescription(productDTO.getDescription());
    return Optional.of(repository.save(productDB));
  }

  @Transactional
  public void deleteProduct(final Long id) {
    repository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<Product> searchProducts(final String prodName) {
    return repository.findByNameContainingIgnoreCase(prodName); // SELECT * FROM Products WHERE name = prodName.toLowercase()
  }
}
