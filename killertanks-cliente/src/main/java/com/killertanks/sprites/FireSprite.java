package com.killertanks.sprites;

import java.awt.Graphics;
import java.awt.Image;

import com.killertanks.comum.Direcao;
import com.killertanks.helper.imagens.ImagensHelper;

/**
 * Classe que define o controle de imagem do usu‡rio que sera printado na tela
 * 
 * @author Yuri Fialho
 * 
 */
public class FireSprite {
	
	private int posX;
	private int posY;
	private Direcao direcao;
	private TankSprite tankSprite;
	private Image image;
	private boolean movendo = false;
	
	public FireSprite(TankSprite tankSprite, int destinoX, int destinoY){
		posX = tankSprite.getX();
		posY = tankSprite.getY();
		direcao = tankSprite.getDirecao();
		image = carregaImagem();
	}
	
	public void move(int x,int y){
				this.posX +=x;
				this.posY +=y;
	}
	
	private Image carregaImagem(){
		ImagensHelper ih = ImagensHelper.getInstance();
		return ih.getTile(69);
	}
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public Direcao getDirecao() {
		return direcao;
	}

	public TankSprite getTankSprite(){
		return tankSprite;
	}
	
	public boolean isMovendo(){
		return movendo;
	}
	
	public void draw(Graphics g) {
        g.drawImage(image, posX, posY, null);
    }
	
}
