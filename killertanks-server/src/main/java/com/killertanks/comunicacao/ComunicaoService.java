package com.killertanks.comunicacao;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.killertanks.comum.Constantes;

public class ComunicaoService {
	
	private static ComunicaoService INSTANCE;
	
	private ComunicaoService(){}
	
	public static ComunicaoService getInstance(){
		if(INSTANCE==null){
			INSTANCE = new ComunicaoService();
		}
		return INSTANCE;
	}
	
	//socket servidor
	private ServerSocket socketServidor;
	//local do servidor
	private String ipServer;
	//porta do servidor
	private int portServer;
	//lista de stream de saida
	private List<ObjectOutputStream> writeAll;
	//numero de usuário logado
	private int numUsuLogado;

	public int getNumUsuLogado() {
		return numUsuLogado;
	}

	public void setNumUsuLogado(int numUsuLogado) {
		this.numUsuLogado = numUsuLogado;
	}

	public String getIpServer() {
		return ipServer;
	}

	public void setIpServer(String ipServer) {
		this.ipServer = ipServer;
	}

	public int getPortServer() {
		return portServer;
	}

	public void setPortServer(int portServer) {
		this.portServer = portServer;
	}

	public ServerSocket getSocketServidor() {
		return socketServidor;
	}

	public void setSocketServidor(ServerSocket socketServidor) {
		this.socketServidor = socketServidor;
	}

	public List<ObjectOutputStream> getWriteAll() {
		return writeAll;
	}

	public void setWriteAll(List<ObjectOutputStream> writeAll) {
		this.writeAll = writeAll;
	}

	/**
	 * Inicia o serviço de rede, verificando se será utilizado a porta e
	 * endereço padrão
	 * 
	 * @return
	 */
	public boolean setUpNetworking(){
		writeAll = new ArrayList<ObjectOutputStream>();
		try {
			verificarHostPorta();
			socketServidor = new ServerSocket(getPortServer());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Verifica se a Ip do servidor foi inicializado e se a porta do servidor
	 * foi inicializada, caso elas não tenham sido inicializadas, será colocada
	 * a porta default(9906) e o ip padrão(127.0.0.1).
	 * 
	 * @author Yuri Fialho
	 */
	private void verificarHostPorta(){
		if(getIpServer()==null||getIpServer().trim().equals("")){
			setIpServer(Constantes.ConstantesServidor.HOST_PADRAO);
		}
		if(getPortServer()==0){
			setPortServer(Constantes.ConstantesServidor.PORTA_PADRAO);
		}
	}
	
	/**
	 * Envia a mensagem de comando para todos os usuário logados
	 * @param message
	 */
	@SuppressWarnings("unchecked")
	public void sendMessagemAll(Menssagem message){
		System.out.println("ENVIANDO MENSAGEM....");
		Iterator it = writeAll.iterator();
		while(it.hasNext() && writeAll.size() > 1){
			try{
				ObjectOutputStream obj = (ObjectOutputStream) it.next();
				obj.writeObject(message);
				obj.flush();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("MENSAGEM ENVIADA...");
	}
	
	/**
	 * Envia a mensagem para um unico usuário
	 * @param usuario
	 * @param msg
	 */
	public void sendMessageReturn(int usuario, Menssagem msg){
		try {
			ObjectOutputStream obj = writeAll.get(usuario);
			obj.writeObject(msg);
			obj.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println("teste");
	}
	
}



