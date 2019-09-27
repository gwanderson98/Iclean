package com.example.Iclean.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.Iclean.entities.enums.StatusOrdemServico;

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
@Table(name = "tb_ordemServico")
public class OrdemServico implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date dataInclusao;
	private StatusOrdemServico status;
	private int avaliacaoCliente;
	private int avaliacaoPrestador;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Usuario cliente = new Usuario();
	
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco = new Endereco();
	
	@ManyToOne
	@JoinColumn(name = "anuncio_id")
	private Anuncio anuncio = new Anuncio();
	
}
