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

import com.example.Iclean.dto.EspecialidadeDTO;
import com.example.Iclean.entities.Especialidade;
import com.example.Iclean.repositories.EspecialidadeRepository;
import com.example.Iclean.services.exceptions.DatabaseException;
import com.example.Iclean.services.exceptions.ResourceNotFoundException;

@Service
public class EspecialidadeService {

	@Autowired
	private EspecialidadeRepository repository;
	

	public List<EspecialidadeDTO> findAll() {
		List<Especialidade> list = repository.findAll();
		return list.stream().map(e -> new EspecialidadeDTO(e)).collect(Collectors.toList());
	}

	public EspecialidadeDTO findById(Long id) {
		Optional<Especialidade> obj = repository.findById(id);
		Especialidade entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new EspecialidadeDTO(entity);
	}

	public Especialidade insert(Especialidade obj) {
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
	public EspecialidadeDTO update(Long id, EspecialidadeDTO dto) {
		try {
			Especialidade entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new EspecialidadeDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Especialidade entity, EspecialidadeDTO dto) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setUsuarios(dto.getUsuarios());
		entity.setAnuncios(dto.getAnuncios());
	}
}
