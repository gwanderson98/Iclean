package com.example.Iclean.dto;

import java.io.Serializable;
import java.util.List;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.Especialidade;
import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String cpf;
	private String senha;
	private String email;
	private List<Endereco> enderecos;
	private List<Anuncio> anuncios;
	private List<OrdemServico> OrdemServicos;
	private List<Especialidade> especialidades;
	
	public UsuarioDTO(Usuario entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.cpf = entity.getCpf();
		this.senha = entity.getSenha();
		this.email = entity.getEmail();
		this.enderecos = entity.getEnderecos();
//		this.anuncios = entity.getAnuncios();
//		this.OrdemServicos = entity.getOrdemServicos();
//		this.especialidades = entity.getEspecialidades();
	}

	public Usuario toEntity() {
		return new Usuario(id, nome, cpf, senha, email,enderecos);
	}
}