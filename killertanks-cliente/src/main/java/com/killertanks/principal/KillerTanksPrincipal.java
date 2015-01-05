package com.killertanks.principal;

import com.killertanks.comunicacao.ComunicaoService;
import com.killertanks.visao.panel.PrincipalPanel;

/**
 * Classe de acesso da aplicação
 * @author Yuri Fialho
 *
 */
public class KillerTanksPrincipal {

	/**
	 * @param Host servidor
	 * @param Port servidor
	 */
	public static void main(String[] args) {
		PrincipalPanel kt = new PrincipalPanel();
		setarHostPortaServer(args);
		kt.start();
	}

	/**
	 * Verifica se algum parametro foi informado se seta os valores para serem
	 * utilizado posteriormente
	 * 
	 * @param args
	 */
	private static void setarHostPortaServer(String[] args){
		ComunicaoService comService = ComunicaoService.getInstance();
		if(args.length == 1 && args[0]!=null && !args[0].trim().equals("")){
			comService.setIpServer(args[0]);
		}
		if(args.length == 2 && args[0]!=null && !args[0].trim().equals("") && args[1]!=null && !args[1].trim().equals("")){
			comService.setIpServer(args[0]);
			comService.setPortServer(Integer.parseInt(args[1]));
		}
	}

}
