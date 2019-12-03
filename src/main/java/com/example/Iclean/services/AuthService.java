package com.example.Iclean.services;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Iclean.dto.CredentialsDTO;
import com.example.Iclean.dto.TokenDTO;
import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.repositories.UsuarioRepository;
import com.example.Iclean.security.JWTUtil;
import com.example.Iclean.services.exceptions.JWTAuthenticationException;
import com.example.Iclean.services.exceptions.JWTAuthorizationException;
import com.example.Iclean.services.exceptions.ResourceNotFoundException;

@Service
public class AuthService {

private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository userRepository;

	@Transactional(readOnly = true)
	public TokenDTO authenticate(CredentialsDTO dto) {
		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getEmail(),
					dto.getSenha());
			authenticationManager.authenticate(authToken);
			String token = jwtUtil.generateToken(dto.getEmail());
			Long id = userRepository.findByEmail(dto.getEmail()).getId();
			return new TokenDTO(id, dto.getEmail(), token);
		} catch (AuthenticationException e) {
			throw new JWTAuthenticationException("Bad credentials");
		}
	}
	
	public Usuario authenticated() {
		try {
			UserDetails userdetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return userRepository.findByEmail(userdetails.getUsername());
		} catch (Exception e) {
			throw new JWTAuthorizationException("Acess denied!");
		}
	}
		
	public void validateSelfOrAdmin (Long userId) {
		Usuario usuario = authenticated();
		if(usuario == null  || (!usuario.getId().equals(userId)) && !usuario.hasRole("ROLE_ADMIN")) {
			throw new JWTAuthorizationException("Acess denied!");
		}
	}
	
	public void validateSelf(Long userId) {
		Usuario usuario = authenticated();
		if(usuario == null  || (!usuario.getId().equals(userId))) {
			throw new JWTAuthorizationException("Acess denied!");
		}
	}
	
	public void validateOwnEndereco(Endereco endereco) {
		Usuario usuario = authenticated();
		if(usuario == null  || (!usuario.getId().equals(endereco.getUsuario().getId()))) {
			throw new JWTAuthorizationException("Acess denied!");
		}
	}
		
	public TokenDTO refreshToken() {
		Usuario usuario = authenticated();
		return new TokenDTO(usuario.getEmail(), jwtUtil.generateToken(usuario.getEmail()));
	}
	
	
	@Transactional
	public void sendNewPassword(String email) {
		Usuario usuario = userRepository.findByEmail(email);
		if(usuario == null) {
			throw new ResourceNotFoundException("Email not found!");
		}
		
		String newPass = newPassword();
		usuario.setSenha(passwordEncoder.encode(newPass));
		
		userRepository.save(usuario);
		
		LOG.info("New password: " + newPass);		
	}	
	
	private String newPassword() {
		char[] vect = new char[10];
		for(int i=0; i<10; i++) {
			vect[i] = randomChar();
		}
		return new String(vect);		
	}
	
	private char randomChar() {
		Random rand = new Random();
		int opt = rand.nextInt(3);
		if(opt == 0) {//generate digit
			return (char) (rand.nextInt(10) + 48);
		}
		else if(opt == 1) {//generate uppercase letter
			return (char) (rand.nextInt(26) + 65);
		}
		else {//generate lowercase letter
			return (char) (rand.nextInt(26) + 97);
		}
	}	
}
