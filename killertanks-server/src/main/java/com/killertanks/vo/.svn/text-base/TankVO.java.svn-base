package com.killertanks.vo;

import java.io.Serializable;

import com.killertanks.comum.Constantes;
import com.killertanks.comum.Direcao;

public class TankVO implements Serializable {
	
	private static final long serialVersionUID = 8509268104170028044L;

	private String userName;
	
	private int posX;
	
	private int posY;
	
	private int numeroHits;
	
	private boolean morto;
	
	private Direcao direcao;
	
	public TankVO(){}
	
	public TankVO(int posX, int posY, Direcao direcao){
		this.posX = posX;
		this.posY = posY;
		this.direcao = direcao;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getNumeroHits() {
		return numeroHits;
	}

	public void setNumeroHits(int numeroHits) {
			this.numeroHits = numeroHits;
	}

	public boolean isMorto() {
		return morto;
	}

	public void setMorto(boolean morto) {
		this.morto = morto;
	}

	public Direcao getDirecao() {
		return direcao;
	}

	public void setDirecao(Direcao direcao) {
		this.direcao = direcao;
	}
	
	public void ferir(){
		if(numeroHits<Constantes.Tank.NUMERO_MORTE){
			numeroHits++;
			setMorto(false);
		}else{
			setMorto(true);
		}
		
	}
}
