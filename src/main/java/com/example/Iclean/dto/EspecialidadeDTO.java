package com.example.Iclean.dto;

import java.io.Serializable;

import com.example.Iclean.entities.Especialidade;

public class EspecialidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;

	public EspecialidadeDTO() {

	}

	public EspecialidadeDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public EspecialidadeDTO(Especialidade entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
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

	public Especialidade toEntity() {
		return new Especialidade(id, nome);
	}
}