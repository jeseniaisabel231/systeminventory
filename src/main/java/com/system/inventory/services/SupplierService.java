package com.system.inventory.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.inventory.dto.SupplierDTO;
import com.system.inventory.mapper.SupplierMapper;
import com.system.inventory.models.Supplier;
import com.system.inventory.repositories.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {
	private final SupplierRepository repository;

	private final SupplierMapper supplierMapper;

	@Transactional
	public Optional<Supplier> createSupplier(final SupplierDTO supplierDTO) {
		final String nameDTO = supplierDTO.getName();
		if (repository.existsByName(nameDTO))
			throw new RuntimeException("Ya existe un proveedor con el nombre " + nameDTO);

		final String emailDTO = supplierDTO.getEmail();
		if (repository.existsByName(emailDTO))
			throw new RuntimeException("Ya existe un email con " + emailDTO);

		final String contactDTO = supplierDTO.getContact();
		if (repository.existsByContact(contactDTO))
			throw new RuntimeException("Y existe un contacto con " + contactDTO);

		final Supplier supplierDB = supplierMapper.toEntity(supplierDTO);

		return Optional.of(repository.save(supplierDB));
	}

	@Transactional(readOnly = true)
	public Optional<Supplier> getSupplierById(final Long id) {
		return repository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Supplier> getAllSuppliers() {
		return repository.findAll();
	}

	@Transactional
	public Optional<Object> updateSupplier(final Long id, final SupplierDTO supplierDTO) {
		final Supplier supplierDB = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("No existe el proveedor con el id que me esta enviando"));

		final String nameDTO = supplierDTO.getName();
		if (!nameDTO.equals(supplierDB.getName()) && repository.existsByName(nameDTO))
			throw new RuntimeException("Ya existe un proveedor con ese nombre");

		final String emailDTO = supplierDTO.getEmail();
		if (!emailDTO.equals(supplierDB.getEmail()) && repository.existsByEmail(emailDTO))
			throw new RuntimeException("Ya existe un proveedor con ese email");

		final String contactDTO = supplierDTO.getContact();
		if (!contactDTO.equals(supplierDB.getContact()) && repository.existsByContact(contactDTO))
			throw new RuntimeException("Ya existe un proveedor con ese contacto");

		supplierDB.setName(nameDTO);
		supplierDB.setEmail(emailDTO);
		supplierDB.setContact(contactDTO);

		return Optional.of(repository.save(supplierDB));
	}

	@Transactional
	public void deleteSupplier(final Long id) {
		repository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Supplier> searchSuppliers(final String supplName) {
		return repository.findByNameContainingIgnoreCase(supplName);
	}

}
