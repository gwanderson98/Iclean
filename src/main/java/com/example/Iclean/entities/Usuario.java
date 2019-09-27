package com.example.Iclean.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

@Table(name = "tb_usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String cpf;
	private String senha;
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private List<Endereco> enderecos = new ArrayList<>();

//	@JsonIgnore
//	@OneToMany(mappedBy = "prestador", fetch = FetchType.LAZY)
//	private List<Anuncio> anuncios = new ArrayList<>();
//
//	@JsonIgnore
//	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
//	private List<OrdemServico> ordemServicos = new ArrayList<>();
//
//	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY)
//	private List<Especialidade> especialidades = new ArrayList<>();
	
	public Usuario() {
		
	}

	public Usuario(Long id, String nome, String cpf, String senha, String email, List<Endereco> enderecos) {		
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.email = email;
		this.enderecos = enderecos;
	}
	
	public Usuario(Usuario entity) {		
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.cpf = entity.getCpf();
		this.senha = entity.getSenha();
		this.email = entity.getEmail();
		this.enderecos = entity.getEnderecos();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
