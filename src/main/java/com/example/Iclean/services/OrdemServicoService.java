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

import com.example.Iclean.dto.EnderecoDTO;
import com.example.Iclean.dto.OrdemServicoDTO;
import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.entities.enums.StatusOrdemServico;
import com.example.Iclean.repositories.OrdemServicoRepository;
import com.example.Iclean.services.exceptions.DatabaseException;
import com.example.Iclean.services.exceptions.ResourceNotFoundException;

@Service
public class OrdemServicoService {

	@Autowired
	private AuthService authService;
	
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
		Usuario client =  authService.authenticated();
		if(client.getId() == obj.getCliente().getId()) {
			return repository.save(obj);
		}
		return null;
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
			authService.validateSelf(entity.getAnuncio().getPrestador().getId());
			entity.setStatus(StatusOrdemServico.CANCELADA);
			entity = repository.save(entity);
			return new OrdemServicoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public OrdemServicoDTO aceitar(Long id) {
		try {
			OrdemServico entity = repository.getOne(id);
			authService.validateSelf(entity.getAnuncio().getPrestador().getId());
			entity.setStatus(StatusOrdemServico.ACEITA);
			entity = repository.save(entity);
			return new OrdemServicoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Transactional
	public OrdemServicoDTO rejeitar(Long id) {
		try {
			OrdemServico entity = repository.getOne(id);
			authService.validateSelf(entity.getAnuncio().getPrestador().getId());
			entity.setStatus(StatusOrdemServico.REJEITADA);
			entity = repository.save(entity);
			return new OrdemServicoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Transactional
	public OrdemServicoDTO concluir(Long id) {
		try {
			OrdemServico entity = repository.getOne(id);
			authService.validateSelf(entity.getAnuncio().getPrestador().getId());
			entity.setStatus(StatusOrdemServico.CONCLUIDA);
			entity = repository.save(entity);
			return new OrdemServicoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Transactional
	public OrdemServicoDTO avaliarcliente(Long id, int avaliacao) {
		try {
			OrdemServico entity = repository.getOne(id);
			authService.validateSelf(entity.getAnuncio().getPrestador().getId());
			entity.setAvaliacaoCliente(avaliacao);;
			entity = repository.save(entity);
			return new OrdemServicoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Transactional
	public OrdemServicoDTO avaliarprestador(Long id, int avaliacao) {
		try {
			OrdemServico entity = repository.getOne(id);
			authService.validateSelf(entity.getCliente().getId());
			entity.setAvaliacaoPrestador(avaliacao);
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

	public List<OrdemServicoDTO> findByUsuario() {
		Usuario client =  authService.authenticated();
		List<OrdemServico> list = repository.findByCliente(client);
		return list.stream().map(e -> new OrdemServicoDTO(e)).collect(Collectors.toList());
	}
}
