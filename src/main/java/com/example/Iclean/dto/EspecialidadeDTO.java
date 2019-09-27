package com.example.Iclean.dto;

import java.io.Serializable;
import java.util.List;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.Especialidade;
import com.example.Iclean.entities.Usuario;

public class EspecialidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private List<Usuario> usuarios;
	private List<Anuncio> anuncios;

	public EspecialidadeDTO() {

	}

	public EspecialidadeDTO(Long id, String nome, List<Usuario> usuarios, List<Anuncio> anuncios) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuarios = usuarios;
		this.anuncios = anuncios;
	}

	public EspecialidadeDTO(Especialidade entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.usuarios = entity.getUsuarios();
		this.anuncios = entity.getAnuncios();
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}

	public Especialidade toEntity() {
		return new Especialidade(id, nome, usuarios, anuncios);
	}
}