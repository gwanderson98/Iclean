package com.example.Iclean.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_avaliacao")
public class Avaliacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer nota;
	private String comentario;
	
	public Avaliacao () {
		
	}

	public Avaliacao(Integer nota, String comentario) {
		super();
		this.nota = nota;
		this.comentario = comentario;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	

}
