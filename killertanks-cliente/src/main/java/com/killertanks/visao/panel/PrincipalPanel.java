package com.killertanks.visao.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.killertanks.comum.Direcao;
import com.killertanks.comum.TipoUsuario;
import com.killertanks.comunicacao.ComunicaoService;
import com.killertanks.comunicacao.Menssagem;

public class PrincipalPanel extends JFrame {
	
	private static final long serialVersionUID = 1877575133579789342L;
	
	private ComunicaoService comunicacaoService;
	private boolean conexaoEstabelecida;
	
	//Menu da aplicaçnao 
	private JMenuBar menuBar;
	
	
	private GamePanel gamePanel;
	private StatusPanel statusPanel;
	
	private Thread playerOne;
	private TipoUsuario usuarioSelecionado;
	

	/**
	 * Método que inicializa a aplicação
	 * @param args
	 */
	public void start() {
		//inicializa os componentes da aplicação
		this.setUpApp();
		//seta a captura do teclado na tela principal
		this.addKeyListener(new PrincipalKeyListener());
		
		playerOne = new Thread(gamePanel);
	}
	
	public void setUpVisao(){
		menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu("File");

		JMenuItem itemConecetar = new JMenuItem("Conectar");
			itemConecetar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
						conexaoEstabelecida = comunicacaoService.setUpNetworking();
						if(conexaoEstabelecida){
							usuarioSelecionado = comunicacaoService.escolherUsuario();
							comunicacaoService.setTipoUsuario(usuarioSelecionado);
							atualizarStatus();
							playerOne.start();
						}
				}
			});
		menuFile.add(itemConecetar);
		
		JMenuItem itemQuit = new JMenuItem("Quit");
			itemQuit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try{
						comunicacaoService.shuttDownNetworking();
					}catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.toString(), "ERRO", JOptionPane.ERROR_MESSAGE);
					}finally{	
						System.exit(0);
					}
				}
			});
		menuFile.add(itemQuit);
		
		menuBar.add(menuFile);	
		
		this.setJMenuBar(menuBar);
		//seta a posição dos paineis
		this.add(gamePanel,BorderLayout.CENTER);
		this.add(statusPanel,BorderLayout.SOUTH);
		//seta tamanho da tela
		this.setBounds(0, 0, 513, 580);
		//informa que será visivel
		this.setVisible(true);
		//nao será permitido redimessionar
		this.setResizable(false);
		//titulo da tela
		this.setTitle("KILLER TANK - FIC - V1.0-BETA");
	}
	
	public void setUpApp(){
		comunicacaoService = ComunicaoService.getInstance();
		gamePanel = new GamePanel();
		statusPanel = new StatusPanel();
		setUpVisao();
	}

	/**
	 * Classe interna que faz o tratamento das ações de teclado para o painel
	 * principal, capturando assim os comandos de movimentação dos usuário.
	 * 
	 * @author yurifialho
	 * @since 1.0
	 * 
	 */
	class PrincipalKeyListener implements KeyListener{
		public void keyPressed(KeyEvent e) {

			Menssagem msg = null;
			switch (e.getKeyCode()) {
			case 39:{
					msg = gamePanel.moverRemoto(Direcao.LESTE);
					break;
				}
			case 37:{
					msg = gamePanel.moverRemoto(Direcao.OESTE);
					break;
				}
			case 40:{
					msg = gamePanel.moverRemoto(Direcao.SUL);
					break;
				}
			case 38:{
					msg = gamePanel.moverRemoto(Direcao.NORTE);
					break;
				}
			case 32:{
					msg = gamePanel.atirarRemoto(usuarioSelecionado);
					break;
				}
			}
			if(msg!=null){
				try {
					comunicacaoService.sendMessagem(msg);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Falha no envio da mensagem!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		public void keyReleased(KeyEvent e) {}

		public void keyTyped(KeyEvent e) {}
		
	}
	
	/**
	 * Atualiza as informações de status do usuario e da aplicação
	 */
	private void atualizarStatus(){
		String statusConexao;
		if(conexaoEstabelecida){
			statusConexao = "CONECTADO";
		}else{
			statusConexao = "DESCONECTADO";
		}
		statusPanel.getStatusConexao().setText(statusConexao);
		statusPanel.getJogadorName().setText("PLAYER "+ usuarioSelecionado);
		statusPanel.repaint();
	}
	
}
