package com.example.Iclean.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private String senha;
	private String email;
	

	@JsonIgnore
	@OneToMany(mappedBy = "usuario")	
	private Set<Endereco> enderecos = new HashSet<>();	
	
	//@JsonIgnore
	@OneToMany(mappedBy = "prestador")
	private List<Anuncio> anuncios = new ArrayList<>();
	
	//@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<OrdemServico> OrdemServicos = new ArrayList<>();
	
	//@JsonIgnore
	@ManyToMany
	private List<Especialidade> especialidades = new ArrayList<>();
	

	public Usuario() {
		
	}

	public Usuario(Long id, String nome, String cpf, String senha, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.email = email;		
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
	
	public Set<Endereco> getEnderecos() {
		return enderecos;
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
