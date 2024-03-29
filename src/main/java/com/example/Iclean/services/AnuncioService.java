package com.example.Iclean.services;

import java.util.ArrayList;
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

import com.example.Iclean.dto.AnuncioDTO;
import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.PalavraChave;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.repositories.AnuncioRepository;
import com.example.Iclean.repositories.PalavraChaveRepository;
import com.example.Iclean.services.exceptions.DatabaseException;
import com.example.Iclean.services.exceptions.ResourceNotFoundException;

@Service
public class AnuncioService {

	@Autowired
	private AuthService authService;

	@Autowired
	private AnuncioRepository repository;

	@Autowired
	private PalavraChaveRepository palavraRepository;

	public Page<AnuncioDTO> findAllPaged(Pageable pageable, Boolean status) {
		Page<Anuncio> list;
		if(status) {
			list = repository.findAllByAtivo(pageable);
		}else {
			list = repository.findAll(pageable);
		}
		return list.map(e -> new AnuncioDTO(e));
	}

	@Transactional(readOnly = true)
	public List<PalavraChave> findPalavrasChaves(Long id) {
		Optional<Anuncio> obj = repository.findById(id);
		Anuncio entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return palavraRepository.findByAnuncio(entity);
	}

	public AnuncioDTO findById(Long id) {
		Optional<Anuncio> obj = repository.findById(id);
		Anuncio entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new AnuncioDTO(entity);
	}

	public AnuncioDTO insert(AnuncioDTO dto) {
		Usuario usuario = authService.authenticated();
		authService.validateSelf(usuario.getId());
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
		authService.validateSelfOrAdmin(dto.getUsuarioId());
		try {
			Anuncio entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new AnuncioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public List<AnuncioDTO> anuncioEspecialidade(Long id) {
		List<Anuncio> list = repository.findAll();
		List<Anuncio> listCerto = new ArrayList<>();
		for (Anuncio anuncio : list) {
			if (anuncio.getEspecialidade().getId() == id) {
				listCerto.add(anuncio);
			}
		}
		return listCerto.stream().map(e -> new AnuncioDTO(e)).collect(Collectors.toList());
	}

	public List<AnuncioDTO> anuncioPalavraChave(String palavra) {
		List<PalavraChave> listPalavras = palavraRepository.findAll();
		List<Anuncio> listAnuncios = new ArrayList<>();
		for (PalavraChave palavraChave : listPalavras) {
			if (palavraChave.getTexto().toLowerCase().contains(palavra.toLowerCase())) {
				listAnuncios.add(palavraChave.getAnuncio());
			}
		}
		return listAnuncios.stream().map(e -> new AnuncioDTO(e)).collect(Collectors.toList());
	}
	
	
	public Page<AnuncioDTO> anuncioTitulo(String palavra,Pageable pageable) {
		Page<Anuncio> list;
		if(palavra.equals("")) {
			list = repository.findAll(pageable);
		}else {
			list = repository.findByTitulo(palavra,pageable);
		}
		return list.map(e -> new AnuncioDTO(e));
	}

	private void updateData(Anuncio entity, AnuncioDTO dto) {
		entity.setTitulo(dto.getTitulo());
		entity.setDescricao(dto.getDescricao());
		entity.setPreco(dto.getPreco());
	}

	@Transactional
	public void alterarStatus(Long id) {
		Usuario usuario = authService.authenticated();
		authService.validateSelfOrAdmin(usuario.getId());
		Anuncio entity = repository.getOne(id);
		entity.setStatus(!entity.getStatus());
		entity = repository.save(entity);
	}

}
