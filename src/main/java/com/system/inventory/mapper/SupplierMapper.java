package com.system.inventory.mapper;

import org.mapstruct.Mapper;

import com.system.inventory.dto.SupplierDTO;
import com.system.inventory.models.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
	SupplierDTO toDto(Supplier supplier);
	Supplier toEntity(SupplierDTO supplierDTO);
}
