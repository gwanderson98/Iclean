package com.example.Iclean.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id") 
@Table(name = "tb_anuncio")
public class Anuncio implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	private Double preco;
	
	@ManyToOne
	@JoinColumn(name = "prestador_id")
	private Usuario prestador = new Usuario();
	
	@JsonIgnore
	@OneToMany(mappedBy = "anuncio")
	private List<OrdemServico> OrdemServicos = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "especialidade_id")
	private Especialidade especialidade = new Especialidade();
	
}
