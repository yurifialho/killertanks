package com.killertanks.comunicacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.killertanks.comum.Constantes;
import com.killertanks.comum.TipoUsuario;

/**
 * Classe singleton que inicializa e o serviço de conexão com o servidor remoto,
 * faz o evio de mensagem cuida de todos os serviços de comunicação.
 * 
 * @author Yuri Fialho
 * 
 */
public class ComunicaoService {
	
	private static ComunicaoService INSTANCE;
	
	private ComunicaoService(){}
	
	public static ComunicaoService getInstance(){
		if(INSTANCE==null){
			INSTANCE = new ComunicaoService();
		}
		return INSTANCE;
	}
	
	//Socket que estabelece a conexão com servidor
	private Socket socketCliente;
	//endereço do servidor
	private String ipServer;
	//porta do serviço no servidor
	private int portServer;
	//Stream de entrada
	private ObjectInputStream reader;
	//Stream de saída
	private ObjectOutputStream writer;
	//Tipo do usuario conectado
	private TipoUsuario tipoUsuario;

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public ObjectInputStream getReader() {
		return reader;
	}

	public void setReader(ObjectInputStream reader) {
		this.reader = reader;
	}

	public ObjectOutputStream getWriter() {
		return writer;
	}

	public void setWriter(ObjectOutputStream writer) {
		this.writer = writer;
	}

	public Socket getSocketCliente() {
		return socketCliente;
	}

	protected void setSocketCliente(Socket socketCliente) {
		this.socketCliente = socketCliente;
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

	/**
	 * Estabelece a conexão com o servidor que encontra-se no IpServer e no
	 * PortServer, preenchendo a saida de dados em forma de objetos
	 * serializaveis e a entrada de objetos em forma de objetos serializaveis,
	 * retornando true se a conexão foi estabelecida e false caso ocorra
	 * qualquer problema.
	 * 
	 * @author Yuri Fialho
	 * @return true se a conexão for estabelecida com o servidor
	 * @return false se a conexão não for estabelecida
	 */
	public boolean setUpNetworking(){
		try{
			//verifica se vai usuar a porta padrão ou outra porta alternativa
			verificarHostPorta();
			//pega o endreço do servidor
			InetAddress address = InetAddress.getByName(getIpServer());
			//inicia a conexão com o servidor
			socketCliente = new Socket(address, getPortServer());
			//seta o stream de entrada
			reader = new ObjectInputStream(socketCliente.getInputStream());
			//seta o stream de saída
			writer = new ObjectOutputStream(socketCliente.getOutputStream());
			//retorna que a conexão foi estabelecida
			return true;
		}catch (Exception e) {
			//conexão falhou
			return false;
		}
	}

	/**
	 * Método que finaliza a conexão com o servidor e finaliza os streams de
	 * leitura e escrita, setando todos para null, caso utilizar verificar se as
	 * conexões estão nulas.
	 * 
	 * @author Yuri Fialho
	 * @throws IOException
	 */
	public void shuttDownNetworking() throws IOException {
		//fecha o stream de leitura
		if(reader!=null){
			reader.close();
			reader=null;
		}
		//fecha o stream de escrita
		if(writer!=null){
			writer.close();
			writer=null;
		}
		//fecha a conexão
		if(socketCliente!=null){ 
				socketCliente.close();
				socketCliente = null;
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
	 * Envia a menssagem já criada e devidamente preenchida para o servidor,
	 * executa uma requisição.
	 * 
	 * @author Yuri Fialho
	 * @param message
	 * @throws IOException
	 */
	public void sendMessagem(Menssagem message) throws IOException{
			writer.writeObject(message);
			writer.flush();
	}

	/**
	 * Método que envia uma mensagem para o servidor verifando qual usuário esta
	 * livre para estabelecer a conexão.
	 * 
	 * @author Yuri Fialho
	 * @return
	 */
	public TipoUsuario escolherUsuario(){
		TipoUsuario retorno = null;
		try{
			//Monta a mensagem 
			Menssagem msg = new Menssagem(null,null,TipoUsuario.A);
			//Envia a mensagem
			sendMessagem(msg);
			Menssagem msgRetorno;
			ObjectInputStream input = this.getReader();
			//espera 100 milisegundos
			Thread.sleep(100);
			//verifica a mesagem de retorno e seta o usuário que foi retornado
			while((msgRetorno = (Menssagem) input.readObject()).getTank() == null && msgRetorno.getFire() == null && msgRetorno.getTipoUsuario() != null ){
				retorno = msgRetorno.getTipoUsuario();
				break;
			}
		}catch (Exception e) {
			System.out.println("#ERROR CONEXÃO");
			e.printStackTrace();
		}	
		return retorno;
	}
}
