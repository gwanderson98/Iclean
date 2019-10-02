package com.example.Iclean.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.Especialidade;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.repositories.AnuncioRepository;
import com.example.Iclean.repositories.EnderecoRepository;
import com.example.Iclean.repositories.EspecialidadeRepository;
import com.example.Iclean.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	private AnuncioRepository anuncioRepository;	


	@Override
	public void run(String... args) throws Exception {

		// Usuarios
		Usuario u1 = new Usuario(null, "Maria Brown", "123456789", "123", "rogercomp@gmail.com");
		Usuario u2 = new Usuario(null, "Alex Green", "0374561566", "456", "marcomala@gmail.com");

		// Endereços
		List<Endereco> listaEnd = new ArrayList<>();

		Endereco e1 = new Endereco(null, "Rua A", 100, "complemento A", "Uberlandia", "MG", "38400000");
		Endereco e2 = new Endereco(null, "Rua B", 101, "complemento B", "Uberlandia", "MG", "38411068");

		listaEnd.add(e1);
		listaEnd.add(e2);

		e1.setUsuario(u1);
		e2.setUsuario(u1);

		// salvando endereço
		enderecoRepository.saveAll(Arrays.asList(e1, e2));		
		
		//Anuncio
		Anuncio anun1 = new Anuncio(null,"Teste de anuncio 1", "Anuncio Teste", 10.50);
		Anuncio anun2 = new Anuncio(null,"Teste de anuncio 2", "Anuncio Teste", 11.50);
		
		anun1.setPrestador(u2);
		anun2.setPrestador(u2);
		
		anuncioRepository.saveAll(Arrays.asList(anun1, anun2));
		
		//
		
		
		
		//Especialidade
		Especialidade esp1 = new Especialidade(null, "Carpinteiro");
		Especialidade esp2 = new Especialidade(null, "Especialidade");	
		
		especialidadeRepository.saveAll(Arrays.asList(esp1, esp2));	
		
		
		// salvando usuario
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		
	}
}
