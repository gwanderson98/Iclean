package com.example.Iclean.dto;

import java.io.Serializable;
import java.util.List;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.Especialidade;
import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.Usuario;

public class UsuarioDTO implements Serializable {
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

	public UsuarioDTO() {
			
	}
	
	public UsuarioDTO(Long id, String nome, String cpf, String senha, String email, List<Endereco> enderecos) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.email = email;
		this.enderecos = enderecos;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}
	
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Usuario toEntity() {
		return new Usuario(id, nome, cpf, senha, email, enderecos);
	}
}