package com.killertanks.comunicacao;

import java.io.ObjectInputStream;
import java.net.Socket;
import com.killertanks.comum.TipoUsuario;

/**
 * Classe que trata a conexão de cada usuário idependentemente
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
			//recebe a mensagem do cliente e faz a montagem e identificação da mensagem
			while((msg = (Menssagem)read.readObject()) != null){
				//monta a mensagem do usuario
				montarMensagem(msg);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que faz o tratamento da mensagem, identificando se é uma
	 * solicitação de usuário ou se é um comando do jogo.
	 * 
	 * @param msg
	 */
	private void montarMensagem(Menssagem msg){
		ComunicaoService comService = ComunicaoService.getInstance();
		//verifica se é uma solicitação de usuario
		if(msg != null && msg.getTank() == null && msg.getFire() == null){
			int usuarioLogado = comService.getNumUsuLogado();
			//verifica se é o 1º usuario logado
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
