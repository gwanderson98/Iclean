package com.example.Iclean.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.Iclean.entities.Anuncio;
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
public class AnuncioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	private String descricao;
	private Double preco;
	private Usuario prestador;
	private List<OrdemServico> ordemServicos;
	private Especialidade especialidade;
	
	public AnuncioDTO(Anuncio entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.descricao = entity.getDescricao();
		this.preco = entity.getPreco();	
		this.prestador = entity.getPrestador();
		this.ordemServicos = entity.getOrdemServicos();
		this.especialidade = entity.getEspecialidade();
	}

	public Anuncio toEntity() {
		return new Anuncio(id, titulo, descricao, preco,prestador,ordemServicos,especialidade);
	}
}