package com.killertanks.comunicacao;

import java.io.ObjectInputStream;
import java.net.Socket;
import com.killertanks.comum.TipoUsuario;

/**
 * Classe que trata a conex�o de cada usu�rio idependentemente
 * @author Yuri Fialho
 *
 */
public class ClientHandler implements Runnable {
	//socket do cliente 
	Socket socket;
	//Stream de entrada
	ObjectInputStream read;
	
	public ClientHandler(Socket s){
		try{
			//recebe o socket do cliente
			socket = s;
			//Seta o stream de entrada
			read = new ObjectInputStream(socket.getInputStream());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		Menssagem msg;
		try{
			//recebe a mensagem do cliente e faz a montagem e identifica��o da mensagem
			while((msg = (Menssagem)read.readObject()) != null){
				//monta a mensagem do usuario
				montarMensagem(msg);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo que faz o tratamento da mensagem, identificando se � uma
	 * solicita��o de usu�rio ou se � um comando do jogo.
	 * 
	 * @param msg
	 */
	private void montarMensagem(Menssagem msg){
		ComunicaoService comService = ComunicaoService.getInstance();
		//verifica se � uma solicita��o de usuario
		if(msg != null && msg.getTank() == null && msg.getFire() == null){
			int usuarioLogado = comService.getNumUsuLogado();
			//verifica se � o 1� usuario logado
			if(usuarioLogado == 0){
				msg.setTipoUsuario(TipoUsuario.A);
				comService.setNumUsuLogado(usuarioLogado+1);
			}else{
				msg.setTipoUsuario(TipoUsuario.B);
			}
			//envia a mesangem de retorno 
			comService.sendMessageReturn(usuarioLogado, msg);
		}else{
			//envia o comando para todos os usuarios logados
			comService.sendMessagemAll(msg);
		}
	}
	
}
