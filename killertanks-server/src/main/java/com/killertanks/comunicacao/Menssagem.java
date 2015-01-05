package com.killertanks.comunicacao;

import java.io.Serializable;

import com.killertanks.comum.TipoUsuario;
import com.killertanks.vo.FireVO;
import com.killertanks.vo.TankVO;

/**
 * Classe que define a menssagem que ser� transmitida entre o cliente e o
 * servidor
 * 
 * @author Yuri Fialho
 * 
 */
public class Menssagem implements Serializable {

	private static final long serialVersionUID = -1125951932289812473L;
	//Informa��es do tank
	private TankVO tank;
	//Informa��es do tiro 
	private FireVO fire;
	//Informa��o do usu�rio que esta enviando a mensagem
	private TipoUsuario tipoUsuario;
	//Informa��e importantes
	private String status;
	

	public Menssagem(TankVO tank, FireVO fire, TipoUsuario tipoUsuario) {
		this.tank = tank;
		this.fire = fire;
		this.tipoUsuario = tipoUsuario;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TankVO getTank() {
		return tank;
	}

	public void setTank(TankVO tank) {
		this.tank = tank;
	}

	public FireVO getFire() {
		return fire;
	}

	public void setFire(FireVO fire) {
		this.fire = fire;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
