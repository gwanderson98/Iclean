package com.example.Iclean.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Iclean.dto.EnderecoDTO;
import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.repositories.EnderecoRepository;
import com.example.Iclean.services.exceptions.DatabaseException;
import com.example.Iclean.services.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private EnderecoRepository repository;	

	public Page<EnderecoDTO> findAllPaged(Pageable pageable) {
		
		Page<Endereco> list = repository.findAll(pageable);
		return list.map(e -> new EnderecoDTO(e));
	}

	public EnderecoDTO findById(Long id) {		
		Optional<Endereco> obj = repository.findById(id);
		Endereco entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		authService.validateOwnEndereco(entity);
		return new EnderecoDTO(entity);
	}

	@Transactional
	public EnderecoDTO insert(EnderecoDTO dto) {
		authService.validateSelf(dto.getUsuarioId());
		Endereco entity = dto.toEntity();
		entity = repository.save(entity);
		return new EnderecoDTO(entity); 
	}
	
	public List<EnderecoDTO> findByUsuario() {
		Usuario client =  authService.authenticated();
		List<Endereco> list = repository.findByUsuario(client);
		return list.stream().map(e -> new EnderecoDTO(e)).collect(Collectors.toList());
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
	public EnderecoDTO update(Long id, EnderecoDTO dto) {
		authService.validateSelf(dto.getUsuarioId());
		try {
			Endereco entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new EnderecoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Endereco entity, EnderecoDTO dto) {
		entity.setId(dto.getId());
		entity.setLogradouro(dto.getLogradouro());
		entity.setNumero(dto.getNumero());
		entity.setCidade(dto.getCidade());
		entity.setEstado(dto.getEstado());
		entity.setCep(dto.getCep());
	}
}
