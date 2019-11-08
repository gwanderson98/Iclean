package com.example.Iclean.dto;

import java.io.Serializable;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.PalavraChave;

public class PalavraChaveDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String texto;
	private Long anuncioId;

	public PalavraChaveDTO() {

	}

	public PalavraChaveDTO(Long id, String texto, Long anuncioId) {
		this.id = id;
		this.texto = texto;
		this.anuncioId = anuncioId;
	}

	public PalavraChaveDTO(PalavraChave entity) {
		setId(entity.getId());
		setTexto(entity.getTexto());
		setAnuncioId(anuncioId);
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
	
	public Long getAnuncioId() {
		return anuncioId;
	}

	public void setAnuncioId(Long anuncioId) {
		this.anuncioId = anuncioId;
	}

	public PalavraChave toEntity() {
		Anuncio anuncio = new Anuncio();
		anuncio.setId(anuncioId);
		return new PalavraChave(id, texto, anuncio);
	}
}