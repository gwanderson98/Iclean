package com.example.Iclean.dto;

import java.io.Serializable;

import com.example.Iclean.entities.Endereco;

public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String cidade;
	private String estado;
	private String cep;

	public EnderecoDTO() {

	}

	public EnderecoDTO(Long id, String logradouro, Integer numero, String complemento, String cidade, String estado,
			String cep) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	public EnderecoDTO(Endereco entity) {
		setId(entity.getId());
		setLogradouro(entity.getLogradouro());
		setNumero(entity.getNumero());
		setComplemento(entity.getComplemento());
		setCidade(entity.getCidade());
		setEstado(entity.getEstado());
		setCep(entity.getCep());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Endereco toEntity() {
		return new Endereco(id, logradouro, numero, complemento, cidade, estado, cep);
	}
}