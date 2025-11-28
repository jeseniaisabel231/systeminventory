package com.system.inventory.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.inventory.dto.ProductDTO;
import com.system.inventory.models.Product;
import com.system.inventory.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController { // Controlador de productos: maneja las solicitudes HTTP relacionadas con los
                                 // productos
  @Autowired
  private ProductService productService; // Inyección de dependencia: Servicio de productos

  @GetMapping // Anotación para manejar solicitudes GET
  public List<Product> getAllProducts() {
    return productService.getAllProducts(); // Llama al servicio para obtener todos los productos
  }

  @GetMapping("/{id}") // Anotación para manejar solicitudes GET con un ID de producto
  public ResponseEntity<Object> getProductById(@PathVariable final Long id) { // @PathVariable vincula el ID de la URL
                                                                              // al parámetro del método
    try {
      final Optional<Product> product = productService.getProductById(id); // Llama al servicio para obtener un producto
                                                                           // por su ID
      return ResponseEntity.ok(product); // ResponseEntity es una clase que representa toda la respuesta HTTP
    } catch (final RuntimeException e) {
      return ResponseEntity.badRequest().body(Map.of("response", e.getMessage())); // Manejo de errores: devuelve un
                                                                                   // mensaje de error si el producto no
                                                                                   // se encuentra
    }
  }

  @PostMapping // Anotación para manejar solicitudes POST
  public ResponseEntity<Object> createProduct(@Valid @RequestBody final ProductDTO productDTO) { // @RequestBody vincula el
                                                                                          // cuerpo de la solicitud al
                                                                                          // parámetro del método
    try {
      final Optional<Product> product = productService.createProduct(productDTO); // Llama al servicio para crear un
                                                                                  // nuevo producto
      return ResponseEntity.status(201).body(product);
    } catch (final RuntimeException e) {
      return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
    }
  }

  @PutMapping("/{id}") // Anotación para manejar solicitudes PUT con un ID de producto
  public ResponseEntity<Object> updateProduct(@PathVariable final Long id,@Valid @RequestBody final ProductDTO productDTO) {
    try {
      final Optional<Object> product = productService.updateProducts(id, productDTO); // Llama al servicio para
                                                                                      // actualizar un producto
                                                                                      // existente
      return ResponseEntity.ok(product);
    } catch (final RuntimeException e) {
      return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
    }
  }

  @DeleteMapping("/{id}") // Anotación para manejar solicitudes DELETE con un ID de producto
  public ResponseEntity<Object> deleteProduct(@PathVariable final Long id) {
    try {
      productService.deleteProduct(id); // Llama al servicio para eliminar un producto por su ID
      return ResponseEntity.ok(Map.of("response", "Producto con id " + id + " eliminado exitosamente"));
    } catch (final RuntimeException e) {
      return ResponseEntity.badRequest().body(Map.of("response", e.getMessage()));
    }
  }

  @GetMapping("/search")
  public ResponseEntity<Object> searchProduct(@RequestParam final String prodName) {
    try {
      final List<Product> searchedProducts = productService.searchProducts(prodName);
      return ResponseEntity.ok(searchedProducts);
    } catch (final RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}