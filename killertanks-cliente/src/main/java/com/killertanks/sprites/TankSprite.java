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
public class TankSprite {
	
	private int x;
	private int y;
	private int frame;
	private ImagensHelper imagemHelper;
	private Image[] up;
	private Image[] down;
	private Image[] left;
	private Image[] rigth;
	private Image[] sequenciaAtual;
	private Direcao direcao;

	public TankSprite(int x, int y) {
        imagemHelper = ImagensHelper.getInstance();
        this.x = x;
        this.y = y;
        frame = 0;
        carregaImagens();
        sequenciaAtual = down;
        direcao = Direcao.SUL;
    }

    public Direcao getDirecao() {
		return direcao;
	}

	public void setDirecao(Direcao direcao) {
		this.direcao = direcao;
	}

	public void carregaImagens() {
        down = new Image[4];
        up = new Image[4];
        left = new Image[4];
        rigth = new Image[4];
        //down
        down[0] = imagemHelper.getTile(89);
        down[1] = imagemHelper.getTile(90);
        down[2] = imagemHelper.getTile(91);
        down[3] = imagemHelper.getTile(92);
        //up
        up[0] = imagemHelper.getTile(95);
        up[1] = imagemHelper.getTile(96);
        up[2] = imagemHelper.getTile(97);
        up[3] = imagemHelper.getTile(98);
        //left
        left[0] = imagemHelper.getTile(110);
        left[1] = imagemHelper.getTile(109);
        left[2] = imagemHelper.getTile(108);
        left[3] = imagemHelper.getTile(107);
        //rigth
        rigth[0] = imagemHelper.getTile(101);
        rigth[1] = imagemHelper.getTile(102);
        rigth[2] = imagemHelper.getTile(103);
        rigth[3] = imagemHelper.getTile(104);
    }
    
    public Image getFrame() {
        return sequenciaAtual[frame];
    }

    public void sequenciaCima() {
        sequenciaAtual = up;
    }

    public void sequenciaBaixo() {
        sequenciaAtual = down;
    }

    public void sequenciaEsquerda() {
        sequenciaAtual = left;
    }

    public void sequenciaDireita() {
        sequenciaAtual = rigth;
    }

    public void draw(Graphics g) {
        g.drawImage(getFrame(), x, y, null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void nextFrame() {
        frame++;
        if (frame > 3) {
            frame = 0;
        }
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
