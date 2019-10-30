package com.example.Iclean.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_anuncio")
public class Anuncio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	private Double preco;

	@ManyToOne
	@JoinColumn(name = "especialidade_id")
	private Especialidade especialidade = new Especialidade();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "prestador_id")
	private Usuario prestador = new Usuario();

	@JsonIgnore
	@OneToMany(mappedBy = "anuncio")
	private List<OrdemServico> ordemServicos = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tb_anuncio_palavrachave", joinColumns = @JoinColumn(name = "anuncio_id"), 
	           inverseJoinColumns = @JoinColumn(name = "palavrachave_id"))                
	private Set<PalavraChave> palavrasChaves = new HashSet<>(); 

	public Anuncio() {

	}

	public Anuncio(Long id, String titulo, String descricao, Double preco, Usuario prestador, Especialidade especialidade) {	
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.preco = preco;
		this.prestador = prestador;		
		this.especialidade = especialidade;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Usuario getPrestador() {
		return prestador;
	}

	public void setPrestador(Usuario prestador) {
		this.prestador = prestador;
	}

	public List<OrdemServico> getOrdemServicos() {
		return ordemServicos;
	}

	public void setOrdemServicos(List<OrdemServico> ordemServicos) {
		this.ordemServicos = ordemServicos;
	}
	
	public Set<PalavraChave> getPalavraChave() {
		return palavrasChaves;
	}
//
	public Especialidade getEspecialidade() {
		return especialidade;
	}
//
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
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
		Anuncio other = (Anuncio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
