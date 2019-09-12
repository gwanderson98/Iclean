package com.example.Iclean.entities.enums;

public enum StatusOrdemServico {
	
	ABERTA(1),
	ACEITA(2),
	CANCELADA(3),
	CONCLUIDA(4),
	REJEITADA(5);
	
	private int code;
	
	private StatusOrdemServico(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static StatusOrdemServico valueOf(int code) {
		for(StatusOrdemServico value: StatusOrdemServico.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("codigo StatusOrdemServico Invalido");
	}

}
