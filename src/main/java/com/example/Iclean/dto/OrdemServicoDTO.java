package com.example.Iclean.dto;

import java.io.Serializable;
import java.util.Date;

import com.example.Iclean.entities.Anuncio;
import com.example.Iclean.entities.Endereco;
import com.example.Iclean.entities.OrdemServico;
import com.example.Iclean.entities.Usuario;
import com.example.Iclean.entities.enums.StatusOrdemServico;

public class OrdemServicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataInclusao;
	private StatusOrdemServico status;
	private int avaliacaoCliente;
	private int avaliacaoPrestador;
	private Long clienteId;
	private Long enderecoId;
	private Long anuncioId;

	public OrdemServicoDTO() {

	}



	public OrdemServicoDTO(Long id, Date dataInclusao, StatusOrdemServico status, int avaliacaoCliente,
			int avaliacaoPrestador, Long clienteId, Long enderecoId, Long anuncioId) {		
		this.id = id;
		this.dataInclusao = dataInclusao;
		this.status = status;
		this.avaliacaoCliente = avaliacaoCliente;
		this.avaliacaoPrestador = avaliacaoPrestador;
		this.clienteId = clienteId;
		this.enderecoId = enderecoId;
		this.anuncioId = anuncioId;
	}

	public OrdemServicoDTO(OrdemServico entity) {	
		this.id = entity.getId();
		this.dataInclusao = entity.getDataInclusao();
		this.status = entity.getStatus();
		this.avaliacaoCliente = entity.getAvaliacaoCliente();
		this.avaliacaoPrestador = entity.getAvaliacaoPrestador();
		this.clienteId = entity.getCliente().getId();
		this.enderecoId = entity.getEndereco().getId();
		this.anuncioId = entity.getAnuncio().getId();
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
		
	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Long enderecoId) {
		this.enderecoId = enderecoId;
	}

	public Long getAnuncioId() {
		return anuncioId;
	}

	public void setAnuncioId(Long anuncioId) {
		this.anuncioId = anuncioId;
	}

	public OrdemServico toEntity() {
		Usuario cliente = new Usuario();
		cliente.setId(clienteId);
		Anuncio anuncio = new Anuncio();
		anuncio.setId(anuncioId);
		Endereco endereco = new Endereco();
		endereco.setId(enderecoId);
		return new OrdemServico(id, dataInclusao, status, avaliacaoCliente, avaliacaoPrestador, cliente, endereco, anuncio);
	}
}