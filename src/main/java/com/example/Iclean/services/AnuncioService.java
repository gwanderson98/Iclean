package com.example.Iclean.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Iclean.dto.AnuncioDTO;
import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.Especialidade;
import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.repositories.AnuncioRepository;
import com.example.Iclean.services.exceptions.DatabaseException;
import com.example.Iclean.services.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository repository;
	

	public List<AnuncioDTO> findAll() {
		List<Anuncio> list = repository.findAll();
		return list.stream().map(e -> new AnuncioDTO(e)).collect(Collectors.toList());
	}

	public AnuncioDTO findById(Long id) {
		Optional<Anuncio> obj = repository.findById(id);
		Anuncio entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new AnuncioDTO(entity);
	}

	public Anuncio insert(Anuncio obj) {
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
	public AnuncioDTO update(Long id, AnuncioDTO dto) {
		try {
			Anuncio entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new AnuncioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Anuncio entity, AnuncioDTO dto) {
		entity.setId(dto.getId());
		entity.setTitulo(dto.getTitulo());
		entity.setDescricao(dto.getDescricao());
		entity.setPreco(dto.getPreco());
		entity.setPrestador(dto.getPrestador());
		entity.setOrdemServicos(dto.getOrdemServicos());
		entity.setEspecialidade(dto.getEspecialidade());
	}
}
