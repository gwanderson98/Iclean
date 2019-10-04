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
import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.PalavraChave;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.entities.enums.StatusOrdemServico;
import com.example.Iclean.repositories.AnuncioRepository;
import com.example.Iclean.repositories.EnderecoRepository;
import com.example.Iclean.repositories.EspecialidadeRepository;
import com.example.Iclean.repositories.OrdemServicoRepository;
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

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Override
	public void run(String... args) throws Exception {

		// Cleanup the tables
		usuarioRepository.deleteAllInBatch();
		especialidadeRepository.deleteAllInBatch();
		
		
		// Usuarios
		Usuario u1 = new Usuario(null, "Prestador 1", "123456789", "123", "rogercomp@gmail.com");
		Usuario u2 = new Usuario(null, "Prestador 2", "0374561566", "456", "marcomala@gmail.com");
		
		Usuario c1 = new Usuario(null, "Cliente 1", "123456465", "678", "cliente1@gmail.com");
	//	Usuario c2 = new Usuario(null, "Cliente 2", "456789123", "947", "cliente2@gmail.com");

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

		// Anuncio
		Anuncio anun1 = new Anuncio(null, "Prestador 1", "Anuncio Teste 1", 10.50);
		Anuncio anun2 = new Anuncio(null, "Prestador 2", "Anuncio Teste 2", 11.50);

		anun1.setPrestador(u2);
		anun2.setPrestador(u2);
		
//		OrdemServico ord1 = new OrdemServico(null, null, StatusOrdemServico.ABERTA, 0, 0);
//		OrdemServico ord2 = new OrdemServico(null, null, StatusOrdemServico.ABERTA, 0, 0);
////
//		ord1.setAnuncio(anun1);
//		ord2.setAnuncio(anun2);
////		
//		ord1.setCliente(c1);
//		ord2.setCliente(c1);
//
//		// salvando ordemsServicos
		//ordemServicoRepository.save(ord1);

		// Especialidade
		Especialidade esp1 = new Especialidade(null, "Carpinteiro");
				
		u1.getEspecialidades().add(esp1);
		
		esp1.getUsuarios().add(u1);
		
		
		//Palavras Chave
		PalavraChave palChave1 = new PalavraChave(null, "trabalho com madeira");
		PalavraChave palChave2 = new PalavraChave(null, "moveis em madeira");
		
		anun1.getPalavraChave().add(palChave1);
		anun1.getPalavraChave().add(palChave2);
		
		palChave1.getAnuncios().add(anun1);
		
		//salvando anuncios
		anuncioRepository.saveAll(Arrays.asList(anun1, anun2));
		
		// salvando usuarios
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
	}
}
