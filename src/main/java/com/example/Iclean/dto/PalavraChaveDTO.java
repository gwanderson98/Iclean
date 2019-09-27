package com.example.Iclean.dto;

import java.io.Serializable;

import com.example.Iclean.entities.PalavraChave;

public class PalavraChaveDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String texto;

	public PalavraChaveDTO() {

	}

	public PalavraChaveDTO(Long id, String texto) {
		this.id = id;
		this.texto = texto;
	}

	public PalavraChaveDTO(PalavraChaveDTO entity) {
		super();
		this.id = entity.getId();
		this.texto = entity.getTexto();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public PalavraChaveDTO(PalavraChave entity) {
		this.id = entity.getId();
		this.texto = entity.getTexto();
	}

	public PalavraChave toEntity() {
		return new PalavraChave(id, texto);
	}
}