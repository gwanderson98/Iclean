package com.example.Iclean.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String cpf;
	private String senha;
	
	@Column(unique = true)
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Endereco> enderecos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "prestador")
	private List<Anuncio> anuncios = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<OrdemServico> ordemServicos = new ArrayList<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tb_usuario_especialidades", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "especialidade_id"))                
	private Set<Especialidade> especialidades = new HashSet<>(); 
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_usuario_role", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))   
	private Set<Role> roles = new HashSet<>(); 

	public Usuario() {
		
	}

	public Usuario(Long id, String nome, String cpf, String senha, String email) {		
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;		
		this.email = email;
	}
	
	public Usuario(Usuario entity) {		
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.cpf = entity.getCpf();
		this.senha = entity.getSenha();
		this.email = entity.getEmail();
	}
	
	public List<Anuncio> getAnuncios() {
		return anuncios;
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
	
	public Set<Especialidade> getEspecialidades() {
		return especialidades;
	}	
	
	public Set<Role> getRoles() {
		return roles;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		return roles;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() {		
		return true;
	}
	
	public boolean hasRole(String roleName) {
		for (Role role : roles) {
			if (role.getAuthority().equals(roleName)) {
				return true;
			}
		}
		return false;
	}
}
