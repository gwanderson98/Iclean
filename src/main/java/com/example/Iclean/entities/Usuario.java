package com.example.Iclean.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "tb_usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private String senha;
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario",fetch = FetchType.EAGER)
	private List<Endereco> enderecos = new ArrayList<>();

//	@JsonIgnore
//	@OneToMany(mappedBy = "prestador", fetch = FetchType.LAZY)
//	private List<Anuncio> anuncios = new ArrayList<>();
//
//	@JsonIgnore
//	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
//	private List<OrdemServico> ordemServicos = new ArrayList<>();
//
//	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY)
//	private List<Especialidade> especialidades = new ArrayList<>();
	
}
