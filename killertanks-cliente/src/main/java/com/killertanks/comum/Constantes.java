package com.killertanks.comum;

/**
 * Estabelece as contanstas para a aplicação.
 * 
 * @author Yuri Fialho
 *
 */
public interface Constantes {
	
	int LINHA_EIXO_Y_A = 32;
	int LINHA_EIXO_Y_B = 272;
	
	//constantes de serivdor
	interface ConstantesServidor{
		String HOST_PADRAO = "127.0.0.1";
		int PORTA_PADRAO = 9906;
	}
	
	interface Tank{
		int NUMERO_MORTE = 2;
	}
	
	interface Keys {
		//BOTAO DIREITA
		int DIREITA = 39;
		//BOTAO ESQUERDA
		int ESQUERDA = 37;
		//BOTAO ACIMA
		int ACIMA = 38;
		//BOTAO ABAIXO
		int ABAIXO = 40;
		//BOTAO SPACE
		int SPACE = 32;
	}
	
	//Constantes referente a apresentação da aplicação
	interface Visao {
		
		int LIMITE_TELA_SUPERIOR = 0;
		int LIMITE_TELA_INFERIOR = 515;
		int LIMITE_TELA_ESQUERDA = 0;
		int LIMITE_TELA_DIREITA = 511;
	
	}
}
