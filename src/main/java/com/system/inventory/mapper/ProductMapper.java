package com.system.inventory.mapper;

import org.mapstruct.Mapper;

import com.system.inventory.dto.ProductDTO;
import com.system.inventory.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductDTO toDto(Product product);
	Product toEntity(ProductDTO productDTO);
}
