package com.example.Iclean.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.Especialidade;
import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.entities.enums.StatusOrdemServico;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date dataInclusao;
	private StatusOrdemServico status;
	private int avaliacaoCliente;
	private int avaliacaoPrestador;
	private Usuario cliente;
	private Endereco endereco;
	private Anuncio anuncio;
	
	public OrdemServicoDTO(OrdemServico entity) {
		this.id = entity.getId();
		this.dataInclusao = entity.getDataInclusao();
		this.status = entity.getStatus();
		this.avaliacaoCliente = entity.getAvaliacaoCliente();
		this.avaliacaoPrestador = entity.getAvaliacaoPrestador();
		this.cliente = entity.getCliente();
		this.endereco = entity.getEndereco();
		this.anuncio = entity.getAnuncio();
	}
	
	public OrdemServico toEntity() {
		return new OrdemServico(id, dataInclusao, status, avaliacaoCliente, avaliacaoPrestador, cliente, endereco, anuncio);
	}
}