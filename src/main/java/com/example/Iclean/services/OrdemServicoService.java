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

import com.example.Iclean.dto.OrdemServicoDTO;
import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.enums.StatusOrdemServico;
import com.example.Iclean.repositories.OrdemServicoRepository;
import com.example.Iclean.services.exceptions.DatabaseException;
import com.example.Iclean.services.exceptions.ResourceNotFoundException;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository repository;
	

	public List<OrdemServicoDTO> findAll() {
		List<OrdemServico> list = repository.findAll();
		return list.stream().map(e -> new OrdemServicoDTO(e)).collect(Collectors.toList());
	}

	public OrdemServicoDTO findById(Long id) {
		Optional<OrdemServico> obj = repository.findById(id);
		OrdemServico entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new OrdemServicoDTO(entity);
	}

	public OrdemServico insert(OrdemServico obj) {
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
	public OrdemServicoDTO update(Long id, OrdemServicoDTO dto) {
		try {
			OrdemServico entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new OrdemServicoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Transactional
	public OrdemServicoDTO cancelar(Long id) {
		try {
			OrdemServico entity = repository.getOne(id);
			entity.setStatus(StatusOrdemServico.CANCELADA);
			entity = repository.save(entity);
			return new OrdemServicoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(OrdemServico entity, OrdemServicoDTO dto) {
		entity.setId(dto.getId());
		entity.setDataInclusao(dto.getDataInclusao());
		entity.setStatus(dto.getStatus());
	}
}
