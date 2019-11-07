	package com.example.Iclean.dto;

import java.io.Serializable;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.Especialidade;
import com.example.Iclean.entities.Usuario;


public class AnuncioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	private String descricao;
	private Double preco;
	private Boolean status;
	private Long usuarioId;
	private Long especialidadeId;
	
		
	public AnuncioDTO() {
		
	}
	
	public AnuncioDTO(Long id, String titulo, String descricao, Double preco, Boolean status, Long usuarioId, Long especialidadeId) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.preco = preco;
		this.status = status;
		this.usuarioId = usuarioId;
		this.especialidadeId = especialidadeId;
	}

	public AnuncioDTO(Anuncio entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.descricao = entity.getDescricao();
		this.preco = entity.getPreco();	
		this.status = entity.getStatus();
		this.usuarioId = entity.getPrestador().getId();
		this.especialidadeId = entity.getEspecialidade().getId();
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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
	
	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getEspecialidadeId() {
		return especialidadeId;
	}

	public void setEspecialidadeId(Long especialidadeId) {
		this.especialidadeId = especialidadeId;
	}

	public Anuncio toEntity() {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioId);
		Especialidade especialidade = new Especialidade();
		especialidade.setId(especialidadeId);
		return new Anuncio(id, titulo, descricao, preco, status, usuario, especialidade);
	}
}