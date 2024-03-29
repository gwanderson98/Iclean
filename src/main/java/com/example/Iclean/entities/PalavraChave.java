package com.example.Iclean.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_palavrachave")
public class PalavraChave implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String texto;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "anuncio_id")	
	private Anuncio anuncio = new Anuncio();
	
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, 
			  cascade = {
		                CascadeType.PERSIST,
		                CascadeType.MERGE
		            },	mappedBy = "palavrasChaves")
	private Set<Anuncio> anuncios = new HashSet<>();
	
	
	public PalavraChave() {
		
	}
	
	public PalavraChave(Long id, String texto, Anuncio anuncio) {
		super();
		this.id = id;
		this.texto = texto;
		this.anuncio = anuncio;
	}
	
	public PalavraChave(PalavraChave entity) {
		setId(entity.getId());
		setTexto(entity.getTexto());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
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
		PalavraChave other = (PalavraChave) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
