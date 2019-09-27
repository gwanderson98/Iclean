package com.example.Iclean.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.Iclean.entities.Endereco;
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
public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String cidade;
	private String estado;
	private String cep;
	private Usuario usuario;
	//private List<OrdemServico> ordemServicos;

	public EnderecoDTO(Endereco entity) {
		this.id = entity.getId();
		this.logradouro = entity.getLogradouro();
		this.numero = entity.getNumero();
		this.complemento = entity.getComplemento();
		this.cidade = entity.getCidade();
		this.estado = entity.getEstado();
		this.cep = entity.getCep();
		this.usuario = entity.getUsuario();
		//this.ordemServicos = entity.getOrdemServicos();
	}

	public Endereco toEntity() {
		return new Endereco(id, logradouro, numero, complemento, cidade, estado, cep, usuario);
	}
}