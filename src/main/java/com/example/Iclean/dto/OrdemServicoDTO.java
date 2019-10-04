package com.example.Iclean.dto;

import java.io.Serializable;
import java.util.Date;

import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.enums.StatusOrdemServico;

public class OrdemServicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataInclusao;
	private StatusOrdemServico status;
	private int avaliacaoCliente;
	private int avaliacaoPrestador;

	public OrdemServicoDTO() {

	}

	public OrdemServicoDTO(Long id, Date dataInclusao, StatusOrdemServico status, int avaliacaoCliente,
			int avaliacaoPrestador) {
		this.id = id;
		this.dataInclusao = dataInclusao;
		this.status = status;
		this.avaliacaoCliente = avaliacaoCliente;
		this.avaliacaoPrestador = avaliacaoPrestador;
	}

	public OrdemServicoDTO(OrdemServico entity) {
		this.id = entity.getId();
		this.dataInclusao = entity.getDataInclusao();
		this.status = entity.getStatus();
		this.avaliacaoCliente = entity.getAvaliacaoCliente();
		this.avaliacaoPrestador = entity.getAvaliacaoPrestador();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public StatusOrdemServico getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}

	public int getAvaliacaoCliente() {
		return avaliacaoCliente;
	}

	public void setAvaliacaoCliente(int avaliacaoCliente) {
		this.avaliacaoCliente = avaliacaoCliente;
	}

	public int getAvaliacaoPrestador() {
		return avaliacaoPrestador;
	}

	public void setAvaliacaoPrestador(int avaliacaoPrestador) {
		this.avaliacaoPrestador = avaliacaoPrestador;
	}

	public OrdemServico toEntity() {
		return new OrdemServico(id, dataInclusao, status, avaliacaoCliente, avaliacaoPrestador);
	}
}