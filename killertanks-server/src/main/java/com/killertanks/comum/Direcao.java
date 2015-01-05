package com.killertanks.comum;

public enum Direcao {
	
	NORTE(1),
	SUL(2),
	LESTE(3),
	OESTE(4),
	NORDESTE(5),
	NOROESTE(6),
	SUDESTE(7),
	SUDOESTE(8);
	
	
	private int direcaoNumerico;
	
	Direcao(int direcaoNumerico){
		this.direcaoNumerico=direcaoNumerico;
	}
	
	public int getCodigo(){
		return direcaoNumerico;
	}
}
