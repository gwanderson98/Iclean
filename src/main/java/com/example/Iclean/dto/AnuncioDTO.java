package com.example.Iclean.dto;

import java.io.Serializable;
import java.util.List;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.Especialidade;
import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.Usuario;


public class AnuncioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	private String descricao;
	private Double preco;
	private Usuario prestador;
	private List<OrdemServico> ordemServicos;
	private Especialidade especialidade;
		
	public AnuncioDTO() {
		
	}
		
	public AnuncioDTO(Long id, String titulo, String descricao, Double preco, Usuario prestador,
			List<OrdemServico> ordemServicos, Especialidade especialidade) {		
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.preco = preco;
		this.prestador = prestador;
		this.ordemServicos = ordemServicos;
		this.especialidade = especialidade;
	}

	
	public AnuncioDTO(Anuncio entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.descricao = entity.getDescricao();
		this.preco = entity.getPreco();	
		this.prestador = entity.getPrestador();
		this.ordemServicos = entity.getOrdemServicos();
		this.especialidade = entity.getEspecialidade();
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

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Anuncio toEntity() {
		return new Anuncio(id, titulo, descricao, preco,prestador,ordemServicos,especialidade);
	}
}