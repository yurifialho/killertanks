package com.killertanks.visao.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel{
	
	private static final long serialVersionUID = -2594607986733499866L;

	public StatusPanel(){
		statusConexao = new JLabel("DESCONECTADO");
		jogadorName = new JLabel("AN�NIMO");
		this.add(jogadorName);
		this.add(statusConexao);
	}
	//Exibe o status da conex�o informando se esta ou n�o conectado
	private JLabel statusConexao;
	//Nome do jogador conectado
	private JLabel jogadorName;
	
	public JLabel getStatusConexao() {
		return statusConexao;
	}

	public void setStatusConexao(JLabel statusConexao) {
		this.statusConexao = statusConexao;
	}

	public JLabel getJogadorName() {
		return jogadorName;
	}

	public void setJogadorName(JLabel jogadorName) {
		this.jogadorName = jogadorName;
	}

	
}
