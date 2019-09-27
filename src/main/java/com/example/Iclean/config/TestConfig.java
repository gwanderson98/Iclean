package com.example.Iclean.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.repositories.EnderecoRepository;
import com.example.Iclean.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
//		Usuario u1 = new Usuario(null, "Maria Brown", "123456789", "123", "rogercomp@gmail.com");
//		Usuario u2 = new Usuario(null, "Alex Green", "0374561566", "456", "marcomala@gmail.com");
//		
//		usuarioRepository.saveAll(Arrays.asList(u1, u2));
//		
//	    Endereco e1 = new Endereco(null,"Rua A", 100, "complemento A", "Uberlandia", "MG", "38400000");
//		Endereco e2 = new Endereco(null,"Rua B", 101, "complemento B", "Uberlandia", "MG", "38411068");
//		Endereco e3 = new Endereco(null,"Rua C", 102, "complemento C", "Uberlandia", "MG", "38400000");
//		Endereco e4 = new Endereco(null,"Rua D", 103, "complemento D", "Uberlandia", "MG", "38411068");
//	
//		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));	

	}
}
