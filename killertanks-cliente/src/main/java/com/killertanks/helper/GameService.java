package com.killertanks.helper;

import com.killertanks.comum.TipoUsuario;
import com.killertanks.sprites.TankSprite;
import com.killertanks.vo.TankVO;

/**
 * Classe que define algumas principais funcionalidades do jogo
 * @author yurifialho
 *
 */
public class GameService {
	
	private static GameService INSTANCE;
	
	private GameService(){}
	
	public static GameService getInstance(){
		if(INSTANCE == null){
			INSTANCE = new GameService();
		}
		return INSTANCE;
	}
	
	//tank do jogador 1
	private TankVO playerA;
	//atributo que indica que ele foi atingido
	private boolean playerAHits = false;
	//tank do jogador 2
	private TankVO playerB;
	//atributo que indica que ele foi atingido
	private boolean playerBHits = false;

	public boolean isPlayerAHits() {
		return playerAHits;
	}

	public void setPlayerAHits(boolean playerAHits) {
		this.playerAHits = playerAHits;
	}

	public boolean isPlayerBHits() {
		return playerBHits;
	}

	public void setPlayerBHits(boolean playerBHits) {
		this.playerBHits = playerBHits;
	}

	public TankVO getPlayerA() {
		return playerA;
	}

	public void setPlayerA(TankVO playerA) {
		this.playerA = playerA;
	}
	
	public void setPlayerA(TankSprite playerA){
		setPlayerA(new TankVO(playerA));
	}
	
	public TankVO getPlayerB() {
		return playerB;
	}

	public void setPlayerB(TankVO playerB) {
		this.playerB = playerB;
	}
	
	public void setPlayerB(TankSprite playerB){
		setPlayerB(new TankVO(playerB));
	}
	
	/**
	 * MŽtodo que verifica se o usu‡rio informado esta morto
	 * @param player
	 * @return true se estiver morto
	 * @return false se estiver vivo 
	 */
	public boolean checkPlayerDead(TipoUsuario player){
		if(player == TipoUsuario.A){
			return playerA.isMorto();
		}else{
			return playerB.isMorto();
		}
	}
	
	/**
	 * Atinge o usu‡rio informado
	 * @param player
	 */
	public void hitPlayer(TipoUsuario player){
		if(player == TipoUsuario.A){
			playerA.ferir();
			playerAHits = true;
		}else{
			playerB.ferir();
			playerBHits = true;
		}
	}
	
	/**
	 * Checa se o usu‡rio informado foi atingido
	 * @param player
	 * @return true se tiver sido atingido
	 * @return false se n‹o tiver sido atingido
	 */
	public boolean checkPlayerHits(TipoUsuario player){
		if(player == TipoUsuario.A){
			return isPlayerAHits();
		}else{
			return isPlayerBHits();
		}
	}
	
	/**
	 * Checa se o usu‡rio advers‡rio foi atingido
	 * @param player
	 * @return true se sim
	 * @return false se n‹o
	 */
	public boolean checkOppositeHits(TipoUsuario player){
		if(player == TipoUsuario.A){
			return isPlayerBHits();
		}else{
			return isPlayerAHits();
		}
	}
}
