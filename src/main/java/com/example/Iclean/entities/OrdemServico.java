package com.example.Iclean.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.Iclean.entities.enums.StatusOrdemServico;

@Entity
@Table(name = "tb_ordemServico")
public class OrdemServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dataInclusao;
	private StatusOrdemServico status;
	private int avaliacaoCliente;
	private int avaliacaoPrestador;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Usuario cliente = new Usuario();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco = new Endereco();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "anuncio_id")
	private Anuncio anuncio = new Anuncio();

	public OrdemServico() {

	}
	
	public OrdemServico(Long id, Date dataInclusao, StatusOrdemServico status, int avaliacaoCliente,
			int avaliacaoPrestador) {		
		this.id = id;
		this.dataInclusao = dataInclusao;
		this.status = status;
		this.avaliacaoCliente = avaliacaoCliente;
		this.avaliacaoPrestador = avaliacaoPrestador;
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

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
