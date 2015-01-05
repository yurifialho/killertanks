package com.killertanks.comum;

public enum TipoUsuario {
	
	A(1),
	B(2);
	
	private int codigo;
	
	TipoUsuario(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return codigo;
	}
}
