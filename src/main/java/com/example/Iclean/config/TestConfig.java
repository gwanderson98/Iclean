package com.example.Iclean.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.Especialidade;
import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.PalavraChave;
import com.example.Iclean.entities.Role;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.entities.enums.StatusOrdemServico;
import com.example.Iclean.repositories.AnuncioRepository;
import com.example.Iclean.repositories.EnderecoRepository;
import com.example.Iclean.repositories.EspecialidadeRepository;
import com.example.Iclean.repositories.OrdemServicoRepository;
import com.example.Iclean.repositories.PalavraChaveRepository;
import com.example.Iclean.repositories.RoleRepository;
import com.example.Iclean.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private PalavraChaveRepository palavraChaveRepository;
	
	@Autowired
	private RoleRepository roleRepository;	

	@Override
	public void run(String... args) throws Exception {

		// Cleanup the tables
		//usuarioRepository.deleteAllInBatch();
		//especialidadeRepository.deleteAllInBatch();
		
		
		// Usuarios
		Usuario u1 = new Usuario(null, "Joao Marcos", "123456789", passwordEncode.encode("senha123"), "joaoM@gmail.com");
		Usuario u2 = new Usuario(null, "Marco Malagas", "0374561566",  passwordEncode.encode("senha321"), "marcomala@gmail.com");	

		Endereco e1 = new Endereco(null, "Avenida Para ", 100, "Medicina", "Uberlandia", "MG", "38400000", u1);
		Endereco e2 = new Endereco(null, "Rua Afonso Pena", 101, "Porto Alegre", "Uberlandia", "MG", "38411068", u1);
		Endereco e3 = new Endereco(null, "Rua Teste", 301, "asdadasd", "Uberlandia", "MG", "38411078", u2);


		// salvando endereço
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
		//Permissões
		Role perm1 = new Role(null, "ROLE_CLIENT");
		Role perm2 = new Role(null, "ROLE_ADMIN");
		
		roleRepository.saveAll(Arrays.asList(perm1, perm2));
		
		u1.getRoles().add(perm1);
		u2.getRoles().add(perm1);
		u2.getRoles().add(perm2);
		
		//usuarioRepository.save(u1);
		

		Especialidade esp1 = new Especialidade(null, "Carpinteiro");
		
		// Anuncio
		Anuncio anun1 = new Anuncio(null, "Carpintaria na promoção", "Todos os tipos de serviços de carpintaria ao seu alcance", 10.50, true, u1, esp1);
		Anuncio anun2 = new Anuncio(null, "Montagem de Moveis na faixa", "Montagens em geral na metade de preço", 11.50, true, u1, esp1);
		Anuncio anun3 = new Anuncio(null, "Montagem de Moveis na faixa", "Montagens em geral na metade de preço", 11.50, true, u2, esp1);
		//Palavras Chave
		PalavraChave palChave1 = new PalavraChave(null, "trabalho com madeira");
		PalavraChave palChave2 = new PalavraChave(null, "moveis em madeira");
		
		//salvando especialidade
		especialidadeRepository.save(esp1);
		
		//salvando anuncios
		anuncioRepository.saveAll(Arrays.asList(anun1, anun2, anun3));
		
		//salvando palavrasChaves
		palavraChaveRepository.saveAll(Arrays.asList(palChave1, palChave2));
		
		anun1.getPalavraChave().add(palChave1);
		anun2.getPalavraChave().add(palChave1);
		anun2.getPalavraChave().add(palChave2);
		
		//salvando associacoes dos anuncios com palavras chave
		anuncioRepository.saveAll(Arrays.asList(anun1, anun2));
		
		u1.getEspecialidades().add(esp1);
		
		//usuarioRepository.saveAll(Arrays.asList(u1));
		
		
		OrdemServico ord1 = new OrdemServico(null, Date.from(Instant.now()) , StatusOrdemServico.ABERTA, 0, 0, u1, e1, anun1);
		OrdemServico ord2 = new OrdemServico(null, Date.from(Instant.now()) , StatusOrdemServico.CONCLUIDA, 10, 10, u2, e2, anun2);
		
		
		ordemServicoRepository.saveAll(Arrays.asList(ord1, ord2));
		
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		
	}
}
