package com.example.Iclean.dto;

import java.io.Serializable;

public class CredentialsDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;
	private int id;

	public CredentialsDTO() {
		
	}

	public CredentialsDTO(String email, String senha) {	
		this.email = email;
		this.senha = senha;
	}
	
	public CredentialsDTO(int id,String email, String senha) {
		this.id = id;
		this.email = email;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
