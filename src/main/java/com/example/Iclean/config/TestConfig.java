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
		
	    Endereco e1 = new Endereco(null,"Rua A", 100, "complemento A", "Uberlandia", "MG", "38400000", null, null);
		Endereco e2 = new Endereco(null,"Rua B", 101, "complemento B", "Uberlandia", "MG", "38411068", null, null);		
	
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		ArrayList<Endereco> listaEndereco = new ArrayList<Endereco>();
		listaEndereco.add(e1);
		listaEndereco.add(e2);
		
		Usuario u1 = new Usuario(null, "Maria Brown", "123456789", "123", "rogercomp@gmail.com",listaEndereco,null,null,null);
		Usuario u2 = new Usuario(null, "Alex Green", "0374561566", "456", "marcobixona@gmail.com",listaEndereco,null,null,null);	
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		
		e1.setUsuario(u1);
		e2.setUsuario(u2);
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}
