package com.example.Iclean.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.Especialidade;
import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EspecialidadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private List<Usuario> usuarios;
	private List<Anuncio> anuncios;
	
	public EspecialidadeDTO(Especialidade entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.usuarios = entity.getUsuarios();
		this.anuncios = entity.getAnuncios();		
	}
	public Especialidade toEntity() {
		return new Especialidade(id, nome, usuarios, anuncios);
	}
}