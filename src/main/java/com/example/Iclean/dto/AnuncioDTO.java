	package com.example.Iclean.dto;

import java.io.Serializable;

import com.example.Iclean.entities.Anuncio;


public class AnuncioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	private String descricao;
	private Double preco;
		
	public AnuncioDTO() {
		
	}
		
	public AnuncioDTO(Long id, String titulo, String descricao, Double preco) {		
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.preco = preco;
	}

	
	public AnuncioDTO(Anuncio entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.descricao = entity.getDescricao();
		this.preco = entity.getPreco();	
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

	public Anuncio toEntity() {
		return new Anuncio(id, titulo, descricao, preco);
	}
}