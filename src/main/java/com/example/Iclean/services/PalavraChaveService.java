package com.example.Iclean.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Iclean.dto.PalavraChaveDTO;
import com.example.Iclean.entities.PalavraChave;
import com.example.Iclean.repositories.PalavraChaveRepository;
import com.example.Iclean.services.exceptions.DatabaseException;
import com.example.Iclean.services.exceptions.ResourceNotFoundException;

@Service
public class PalavraChaveService {

	@Autowired
	private PalavraChaveRepository repository;
	

	public List<PalavraChaveDTO> findAll() {
		List<PalavraChave> list = repository.findAll();
		return list.stream().map(e -> new PalavraChaveDTO(e)).collect(Collectors.toList());
	}

	public PalavraChaveDTO findById(Long id) {
		Optional<PalavraChave> obj = repository.findById(id);
		PalavraChave entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new PalavraChaveDTO(entity);
	}

	public PalavraChave insert(PalavraChave obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public PalavraChaveDTO update(Long id, PalavraChaveDTO dto) {
		try {
			PalavraChave entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new PalavraChaveDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(PalavraChave entity, PalavraChaveDTO dto) {
		entity.setId(dto.getId());
		entity.setTexto(dto.getTexto());
	}
}
