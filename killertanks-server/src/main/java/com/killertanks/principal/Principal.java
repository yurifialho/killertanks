package com.killertanks.principal;

import java.io.ObjectOutputStream;
import java.net.Socket;

import com.killertanks.comunicacao.ClientHandler;
import com.killertanks.comunicacao.ComunicaoService;

public class Principal {

	public static void main(String[] args) {
		Principal app = new Principal();
		app.setUpApp();
		app.go();
	}
	
	public void setUpApp(){
		System.out.println("...INICIANDO SERVER...");
		//Inicializa o serviço de rede
		ComunicaoService.getInstance().setUpNetworking();
		System.out.println("...INICIOU...");
	}
	
	public void go(){
		try{
			ComunicaoService comService = ComunicaoService.getInstance();
			while(true){
				//espera a conexão do usuário
				Socket cliente = comService.getSocketServidor().accept();
				//adiciona o stream de saida do usuário para a lista 
				comService.getWriteAll().add(new ObjectOutputStream(cliente.getOutputStream()));
				//inicia a thread do clinete para quer elas tratem suas solicitaçoes de forma idependente
				Thread t = new Thread(new ClientHandler(cliente));
				t.start();
				System.out.println("...CONECTOU...");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
