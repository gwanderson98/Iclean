package com.example.Iclean.dto;

import java.io.Serializable;

public class TokenDTO  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String email;
	private String token;
	private Long id;
	
	public TokenDTO() {
		
	}

	public TokenDTO(Long id, String email, String token) {	
		this.id = id;
		this.email = email;
		this.token = token;
	}
	
	public TokenDTO( String email, String token) {
		this.email = email;
		this.token = token;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
