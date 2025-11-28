package com.system.inventory.mapper;

import org.mapstruct.Mapper;

import com.system.inventory.dto.CategoryDTO;
import com.system.inventory.models.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryDTO toDto(Category category);
	Category toEntity(CategoryDTO categoryDTO);
}
