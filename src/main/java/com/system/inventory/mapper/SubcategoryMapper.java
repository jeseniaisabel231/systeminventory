package com.system.inventory.mapper;

import org.mapstruct.Mapper;

import com.system.inventory.dto.SubcategoryDTO;
import com.system.inventory.models.Subcategory;

@Mapper(componentModel = "spring")
public interface SubcategoryMapper {
	SubcategoryDTO toDto(Subcategory subcategory);
	Subcategory toEntity(SubcategoryDTO subcategoryDTO);
}
