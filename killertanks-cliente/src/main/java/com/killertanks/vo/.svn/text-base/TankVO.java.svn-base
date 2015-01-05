package com.killertanks.vo;

import java.io.Serializable;

import com.killertanks.comum.Constantes;
import com.killertanks.comum.Direcao;
import com.killertanks.sprites.TankSprite;

/**
 * Classe que difine as informações relevante para a comunicação
 * @author yurifialho
 *
 */
public class TankVO implements Serializable {
	
	private static final long serialVersionUID = 8509268104170028044L;

	private String userName;
	//posição do usuario horizontal
	private int posX;
	//posição do usuario vertical
	private int posY;
	//numero de vida do usuário
	private int numeroHits = Constantes.Tank.NUMERO_MORTE;
	//se o usuário esta vivo ou morot
	private boolean morto;
	//direção que ele esta voltado
	private Direcao direcao;
	//se ele esta movendo-se ou esta parado
	private boolean movendo;
	
	public TankVO(){}
	
	public TankVO(TankSprite tank){
		setPosX(tank.getX());
		setPosY(tank.getY());
		setDirecao(tank.getDirecao());
	}
	
	public TankVO(int posX, int posY, Direcao direcao){
		this.posX = posX;
		this.posY = posY;
		this.direcao = direcao;
	}

	public boolean isMovendo() {
		return movendo;
	}

	public void setMovendo(boolean movendo) {
		this.movendo = movendo;
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
		if(numeroHits<=0){
			setMorto(true);
			System.out.println(" HIT "+numeroHits);
			System.out.println(" MORTO: "+isMorto());
		}else{
			System.out.println(numeroHits);
			numeroHits--;
			setMorto(false);
			System.out.println(" HIT "+numeroHits);
			System.out.println(" MORTO: "+isMorto());
		}
	}
}
