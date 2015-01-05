package com.killertanks.vo;

import java.io.Serializable;

import com.killertanks.comum.Direcao;

public class FireVO implements Serializable{

	private static final long serialVersionUID = 7808613897155940436L;

	private int posX;
	
	private int posY;
	
	private Direcao direcao;
	
	private TankVO tankOrigem;
	
	public FireVO(){}
	
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

	public Direcao getDirecao() {
		return direcao;
	}

	public void setDirecao(Direcao direcao) {
		this.direcao = direcao;
	}

	public TankVO getTankOrigem() {
		return tankOrigem;
	}

	public void setTankOrigem(TankVO tankOrigem) {
		this.tankOrigem = tankOrigem;
	}

}
