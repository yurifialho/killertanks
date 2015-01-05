package com.killertanks.vo;

import java.io.Serializable;

import com.killertanks.comum.Direcao;
import com.killertanks.sprites.FireSprite;

/**
 * Classe que difine as informações relevante para a comunicação
 * @author yurifialho
 *
 */
public class FireVO implements Serializable{

	private static final long serialVersionUID = 7808613897155940436L;
	//posição do tiro horizontal
	private int posX;
	//posição do tiro vertical
	private int posY;
	//direçao do tiro 
	private Direcao direcao;
	//tank que saiu o tiro
	private TankVO tankOrigem;
	//se o tiro esta movendo-se
	private boolean movendo;
	
	public boolean isMovendo() {
		return movendo;
	}

	public void setMovendo(boolean movendo) {
		this.movendo = movendo;
	}

	public FireVO(){}
	
	public FireVO(FireSprite fire){
		setPosX(fire.getPosX());
		setPosY(fire.getPosY());
		setDirecao(fire.getDirecao());
	}
	
	public FireVO(FireSprite fire, TankVO tank){
		this(fire);
		setTankOrigem(tank);
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
