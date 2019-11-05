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

import com.example.Iclean.dto.AnuncioDTO;
import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.repositories.AnuncioRepository;
import com.example.Iclean.services.exceptions.DatabaseException;
import com.example.Iclean.services.exceptions.ResourceNotFoundException;

@Service
public class AnuncioService {
	
	@Autowired
	private AuthService authService;

	@Autowired
	private AnuncioRepository repository;	
	
	public List<AnuncioDTO> findAll() {
		List<Anuncio> list = repository.findAll();
		return list.stream().map(e -> new AnuncioDTO(e)).collect(Collectors.toList());
	}

	public AnuncioDTO findById(Long id) {
		authService.validateSelfOrAdmin(id);
		Optional<Anuncio> obj = repository.findById(id);
		Anuncio entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new AnuncioDTO(entity);
	}

	public AnuncioDTO insert(AnuncioDTO dto) {
		authService.validateSelf(dto.getUsuarioId());
		Anuncio entity = dto.toEntity();
		entity = repository.save(entity);
		return new AnuncioDTO(entity); 
	}

	public void delete(Long id) {
		authService.validateSelfOrAdmin(id);
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public AnuncioDTO update(Long id, AnuncioDTO dto) {
		authService.validateSelfOrAdmin(id);
		try {
			Anuncio entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new AnuncioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public List<AnuncioDTO> anuncioEspecialidade(AnuncioDTO dto, Long id) {
		authService.validateSelf(dto.getUsuarioId());
		
		return null;
	}

	private void updateData(Anuncio entity, AnuncioDTO dto) {
		entity.setTitulo(dto.getTitulo());
		entity.setDescricao(dto.getDescricao());
		entity.setPreco(dto.getPreco());
	}

	@Transactional
	public void alterarStatus(Long id) {
		authService.validateSelfOrAdmin(id);
		Anuncio entity = repository.getOne(id);
		entity.setStatus(!entity.getStatus());
		entity = repository.save(entity);
	}
}
