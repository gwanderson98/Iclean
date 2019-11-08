package com.example.Iclean.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Iclean.dto.AnuncioDTO;
import com.example.Iclean.dto.UsuarioDTO;
import com.example.Iclean.dto.UsuarioInsertDTO;
import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.repositories.EnderecoRepository;
import com.example.Iclean.repositories.UsuarioRepository;
import com.example.Iclean.services.exceptions.DatabaseException;
import com.example.Iclean.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private AuthService authService;

	public List<UsuarioDTO> findAll() {
		List<Usuario> list = repository.findAll();
		return list.stream().map(e -> new UsuarioDTO(e)).collect(Collectors.toList());
	}

	public UsuarioDTO findById(Long id) {
		authService.validateSelfOrAdmin(id);
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new UsuarioDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<Endereco> findEnderecos(Long id) {		
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return enderecoRepository.findByUsuario(entity);
	}
	
	public UsuarioDTO insert(UsuarioInsertDTO dto) {
		Usuario entity = dto.toEntity();
		entity.setSenha(passwordEncode.encode(dto.getSenha()));
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
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
	public UsuarioDTO update(Long id, UsuarioDTO dto) {
		try {
			authService.validateSelfOrAdmin(id);
			Usuario entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Usuario entity, UsuarioDTO dto) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());		
		entity.setEmail(dto.getEmail());
	}

	@Override
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		return usuario;
	}
	@Transactional(readOnly = true)
	public List<AnuncioDTO> findAnuncios(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return entity.getAnuncios().stream().map(anuncio -> new AnuncioDTO(anuncio)).collect(Collectors.toList());
	}
}
