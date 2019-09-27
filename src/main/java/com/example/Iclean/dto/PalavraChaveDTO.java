package com.example.Iclean.dto;

import java.io.Serializable;

import com.example.Iclean.entities.PalavraChave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PalavraChaveDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String texto;
	
	public PalavraChaveDTO(PalavraChave entity) {
		this.id = entity.getId();
		this.texto = entity.getTexto();		
	}

	public PalavraChave toEntity() {
		return new PalavraChave(id, texto);
	}
}