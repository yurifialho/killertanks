package com.killertanks.visao.panel;

import java.awt.Graphics;
import java.io.ObjectInputStream;

import javax.swing.JPanel;

import com.killertanks.comum.Constantes;
import com.killertanks.comum.Direcao;
import com.killertanks.comum.TipoUsuario;
import com.killertanks.comunicacao.ComunicaoService;
import com.killertanks.comunicacao.Menssagem;
import com.killertanks.helper.GameService;
import com.killertanks.helper.imagens.ImagensHelper;
import com.killertanks.sprites.FireSprite;
import com.killertanks.sprites.TankSprite;
import com.killertanks.vo.FireVO;
import com.killertanks.vo.TankVO;

/**
 * Classe que renderiza toda a animação do jogo como a impressão dos personagens
 * na tela.
 * 
 * @author yurifialho
 * 
 */
public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = -4052223085727738537L;
	// Jogadores
	private TankSprite tankA;
	private TankSprite tankB;
	// Tiros dos Jogadores
	private FireSprite fireA;
	private FireSprite fireB;

	// Helper para o trabalho com imagens
	@SuppressWarnings("unused")
	private ImagensHelper imagensHelper;
	// posicionamento dos personagens
	private int posX_A = 0;
	private int posY_A = 0;
	private int posX_B = 0;
	private int posY_B = 0;
	// posição inicial dos personagens
	private int posicaoInicial_A = 30;
	private int posicaoInicial_B = 330;
	// trata os serviços do jogo
	private GameService gameService = GameService.getInstance();

	int mapa[][] = {
			{ 01, 05, 05, 05, 05, 05, 05, 05, 05, 05, 05, 05, 05, 05, 05, 05,
					05, 05, 05, 02 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 19, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03, 03,
					03, 03, 03, 04 },
			{ 17, 06, 06, 06, 06, 06, 06, 06, 06, 06, 06, 06, 06, 06, 06, 06,
					06, 06, 06, 18 } };

	public GamePanel() {

		imagensHelper = ImagensHelper.getInstance();
		// inicia o personagem A
		tankA = new TankSprite(0, 0);
		tankA.setPosition((posicaoInicial_A % 20) * 16,
				(posicaoInicial_A / 20) * 16);
		// inicia o personagem B
		tankB = new TankSprite(0, 0);
		tankB.setPosition((posicaoInicial_B % 20) * 16,
				(posicaoInicial_B / 20) * 16);
		// seta as posições dos usuários
		posX_A = tankA.getX();
		posY_A = tankA.getY();
		posX_B = tankB.getX();
		posY_B = tankB.getY();
		// seta na classe de serviço os usuários
		gameService.setPlayerA(tankA);
		gameService.setPlayerB(tankB);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Preenche o mapa na tela
		/*int tile[][] = mapa;
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile.length; j++) {
				int x, y;
				x = i * 16;
				y = j * 16;
				g.drawImage(ImagensHelper.getInstance().getTile(tile[j][i]), x,
						y, null);
			}
		}*/
		g.drawImage(ImagensHelper.getInstance(ImagensHelper.BACKGROUND).getImage(),0,0,null);
		// printa o usuário A se ele não estiver morto
		if (!gameService.checkPlayerDead(TipoUsuario.A)) {
			tankA.draw(g);
		}
		// printa o usuário B se ele não estiver morto
		if (!gameService.checkPlayerDead(TipoUsuario.B)) {
			tankB.draw(g);
		}
		// printa o tiro do usuário A se existir tiro
		if (fireA != null) {
			fireA.draw(g);
		}
		// printa o tiro do usuário B se existir tiro
		if (fireB != null) {
			fireB.draw(g);
		}
		// executa ação de verificação de impacto
		hitPlayer();
	}

	/**
	 * Responsável pela movimentação do jogador no jogo, passando-se a direção
	 * do movimento e o player que esta executando o movimento. Criando uma
	 * thread independente para a movimentação do usuário informado na direção
	 * desejada.
	 * 
	 * @author Yuri Fialho
	 * @param direcao
	 * @param player
	 */
	public void andar(final Direcao direcao, final TipoUsuario player) {
		Thread th = new Thread(new Runnable() {
			public void run() {
				try {
					// executa a ação de dar um passo na direção e para o
					// usuario informado
					darUmPasso(direcao, player);
					// repinta a tela com as informações da posição do jogador
					// atualizada
					repaint();
					// pausa a tread em 50 milisegundos para que torne-se
					// perceptivel o movimento do jogador
					Thread.sleep(50);
				} catch (Exception e) {
					System.out.println("#ERRO -- ANDAR");
					e.printStackTrace();
				}
			}
		});
		th.start();
	}

	/**
	 * Método responsável pela movimentação dos players na tela esse método que
	 * realmente faz o usuário andar na tela, verificando que esta movimentando
	 * se é o player A ou player B, verificando se o mesmo encotra-se no limite
	 * do mapa ou não. Faz também após a movimentação a atualização da posição
	 * no panel.
	 * 
	 * @author Yuri Fialho
	 * @param direcao
	 * @param player
	 */
	public void darUmPasso(Direcao direcao, TipoUsuario player) {
		TankSprite tankMovimento = null;
		int posX = 0;
		int posY = 0;
		// verifica o usuário que esta solicitando a movimentação e seta nas
		// variáveis locais sua posição e o seu Sprite.
		if (player == TipoUsuario.A) {
			tankMovimento = tankA;
			posX = tankA.getX();
			posY = tankA.getY();
		} else {
			tankMovimento = tankB;
			posX = tankB.getX();
			posY = tankB.getY();
		}
		// verifica o sentido do movimento
		switch (direcao) {
		// direita
		case LESTE: {
			// verifica se o personagem esta no limite da tela
			if (posX != Constantes.Visao.LIMITE_TELA_DIREITA) {
				// seta a imagem para a sequencia de imagem voltada para a
				// direita
				tankMovimento.sequenciaDireita();
				// apresenta a aproxima imagem dado a impressao de movimento
				tankMovimento.nextFrame();
				// atualiza a posição do usuario
				tankMovimento.move(4, 0);
				// atualiza a direção do usuário
				tankMovimento.setDirecao(Direcao.LESTE);
			}
			break;
		}
			// esquerda
		case OESTE: {
			// verifica se o personagem esta no limite da tela
			if (posX != Constantes.Visao.LIMITE_TELA_ESQUERDA) {
				// seta a imagem para a sequencia de imagem voltada para a
				// direita
				tankMovimento.sequenciaEsquerda();
				// apresenta a aproxima imagem dado a impressao de movimento
				tankMovimento.nextFrame();
				// atualiza a posição do usuario
				tankMovimento.move(-4, 0);
				// atualiza a direção do usuário
				tankMovimento.setDirecao(Direcao.OESTE);
			}
			break;
		}
			// baixo
		case SUL: {
			// verifica se o personagem esta no limite da tela
			if (posY != Constantes.Visao.LIMITE_TELA_INFERIOR) {
				// seta a imagem para a sequencia de imagem voltada para a
				// direita
				tankMovimento.sequenciaBaixo();
				// apresenta a aproxima imagem dado a impressao de movimento
				tankMovimento.nextFrame();
				// atualiza a posição do usuario
				tankMovimento.move(0, 4);
				// atualiza a direção do usuário
				tankMovimento.setDirecao(Direcao.SUL);
			}
			break;
		}
			// cima
		case NORTE: {
			// verifica se o personagem esta no limite da tela
			if (posY != Constantes.Visao.LIMITE_TELA_SUPERIOR) {
				// seta a imagem para a sequencia de imagem voltada para a
				// direita
				tankMovimento.sequenciaCima();
				// apresenta a aproxima imagem dado a impressao de movimento
				tankMovimento.nextFrame();
				// atualiza a posição do usuario
				tankMovimento.move(0, -4);
				// atualiza a direção do usuário
				tankMovimento.setDirecao(Direcao.NORTE);
			}
			break;
		}
		}

		// atualiza a posição dos usuários perante o painel
		if (player == TipoUsuario.A) {
			posX_A = tankMovimento.getX();
			posY_A = tankMovimento.getY();
		} else {
			posX_B = tankMovimento.getX();
			posY_B = tankMovimento.getY();
		}
	}

	/**
	 * Método responsável pelo tiro do usuário, onde o mesmo informa o usuário
	 * que esta atirando, o mesmo será direcionado para o lado que o usuário
	 * estiver voltado.
	 * 
	 * @author Yuri Fialho
	 * @param player
	 */
	public void fire(final TipoUsuario player) {
		// verifica o tipo do usuário
		if (player == TipoUsuario.A) {
			// inicializa o tiro do usuário
			fireA = new FireSprite(tankA, 0, 0);
			// seta no serviço do jogo que o usuario adversário não foi
			// recentemente atingido
			gameService.setPlayerBHits(false);
		} else {
			// inicializa o tiro do usuário
			fireB = new FireSprite(tankB, 0, 0);
			// seta no serviço do jogo que o usuario adversário não foi
			// recentemente atingido
			gameService.setPlayerAHits(false);
		}
		// atualiza a tela
		repaint();
		// Cria uma thread independente para a movimentação do tiro
		Thread th = new Thread(new Runnable() {
			public void run() {
				try {
					// Pega o Sprite do tipo dependendo do usuário
					FireSprite fire = (player == TipoUsuario.A ? fireA : fireB);
					// executa o tiro e toda sua movimentação verificando se o
					// tiro ainda esta em movimento e se o tiro não atingiu o
					// adversário.
					while (direcionarTiro(fire)
							&& !gameService.checkOppositeHits(player)) {
						// atualiza a tela
						repaint();
						// pausa 50 milisegundo para que a movimentação do tiro
						// seja perceptivel
						Thread.sleep(50);
					}
					// destroi o objeto tiro
					if (player == TipoUsuario.A) {
						fireA = null;
					} else {
						fireB = null;
					}
					// retirar o tiro da tela
					repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		th.start();
	}

	/**
	 * Método que executa a movimentação do tiro na tela, atualizando as
	 * posições.
	 * 
	 * @author Yuri Fialho
	 * @param fire
	 * @return true se o tiro estiver movendo-se
	 * @return false se o tiro não estiver movendo-se ou seja chegou ao limite
	 *         do mapa.
	 */
	private boolean direcionarTiro(FireSprite fire) {
		Direcao direcao = fire.getDirecao();
		boolean movendo = false;
		switch (direcao) {
		// acima
		case NORTE: {
			// verifica se o tiro já chegou ao limite da tela
			if (fire.getPosY() > Constantes.Visao.LIMITE_TELA_SUPERIOR) {
				// movimenta o tiro
				fire.move(0, -4);
				// seta que o tiro esta movimentando-se
				movendo = true;
			}
			break;
		}
			// abaixo
		case SUL: {
			// verifica se o tiro já chegou ao limite da tela
			if (fire.getPosY() < Constantes.Visao.LIMITE_TELA_INFERIOR) {
				// movimenta o tiro
				fire.move(0, 4);
				// seta que o tiro esta movimentando-se
				movendo = true;
			}
			break;
		}
			// direita
		case LESTE: {
			// verifica se o tiro já chegou ao limite da tela
			if (fire.getPosX() < Constantes.Visao.LIMITE_TELA_DIREITA) {
				// movimenta o tiro
				fire.move(4, 0);
				// seta que o tiro esta movimentando-se
				movendo = true;
			}
			break;
		}
			// esquesda
		case OESTE: {
			// verifica se o tiro já chegou ao limete da tela
			if (fire.getPosX() > Constantes.Visao.LIMITE_TELA_ESQUERDA) {
				// movimenta o tiro
				fire.move(-4, 0);
				// seta que o tiro esta movimentando-se
				movendo = true;
			}
			break;
		}
		}
		return movendo;
	}

	/**
	 * Monta a mensagem com as informações do tank principal, ou seja do jogador
	 * cliente e a informação do tiro que foi efetuado, se existir algum.
	 * 
	 * @author Yuri Fialho
	 * @param tankPrincipal
	 * @param firePrincipal
	 * @return menssagem que será enviada para o servidor
	 */
	private Menssagem montarMenssagem(TankVO tankPrincipal, FireVO firePrincipal) {
		ComunicaoService comService = ComunicaoService.getInstance();
		Menssagem msg = new Menssagem(tankPrincipal, firePrincipal, comService
				.getTipoUsuario());
		return msg;
	}

	/**
	 * Monta a mensagem de movimentação a partir do tank sprite que estiver
	 * inicializado na aplicação adicionando ou subtraindo os pontos da
	 * movimentação, movimenta um ponto na direção que é informada.
	 * 
	 * @author Yuri Fialho
	 * @param direção
	 * @return menssagem de movimentação pronta para ser enviada para o servidor
	 */
	public Menssagem moverRemoto(Direcao direcao) {
		ComunicaoService comService = ComunicaoService.getInstance();
		TipoUsuario player = comService.getTipoUsuario();
		int posX = 0;
		int posY = 0;
		TankVO tank;
		// seta na o usuário que esta solicitando
		if (player == TipoUsuario.A) {
			// seta o tank do usuario que esta solicitando
			tank = new TankVO(tankA);
			// seta as posiços
			posX = posX_A;
			posY = posY_A;
		} else {
			// seta o tank do usuário que esta solicitando
			tank = new TankVO(tankB);
			// seta as posições
			posX = posX_B;
			posY = posY_B;
		}
		switch (direcao) {
		// direita
		case LESTE: {
			// verifica se o usuário encontra-se no limite da tela
			if (posX != Constantes.Visao.LIMITE_TELA_DIREITA) {
				// modifica o posicionamento
				tank.setPosX(tank.getPosX() + 4);
				// seta a direção
				tank.setDirecao(Direcao.LESTE);
				// seta que esta movendo-se
				tank.setMovendo(true);
			} else {
				// seta que não esta movendo-se
				tank.setMovendo(false);
			}
			break;
		}
			// esquerda
		case OESTE: {
			// Verifica se o usuário encontra-se no limite da tela
			if (posX != Constantes.Visao.LIMITE_TELA_ESQUERDA) {
				// modifica o posicionamento
				tank.setPosX(tank.getPosX() - 4);
				// seta a direção
				tank.setDirecao(Direcao.OESTE);
				// seta que esta movendo-se
				tank.setMovendo(true);
			} else {
				// seta que não esta movendo-se
				tank.setMovendo(false);
			}
			break;
		}
			// baixo
		case SUL: {
			// verifica se o usuário encontra-se no limite da tela
			if (posY != Constantes.Visao.LIMITE_TELA_INFERIOR) {
				// modifica o posicionamento
				tank.setPosY(tank.getPosY() + 4);
				// seta a direção
				tank.setDirecao(Direcao.SUL);
				// seta que esta movimentando-se
				tank.setMovendo(true);
			} else {
				// seta que esta parado
				tank.setMovendo(false);
			}
			break;
		}
			// cima
		case NORTE: {
			// verifica se o usuário encontra-se no limite da tela
			if (posY != Constantes.Visao.LIMITE_TELA_SUPERIOR) {
				// modifica o posicionamento
				tank.setPosY(tank.getPosY() - 4);
				// seta a direção
				tank.setDirecao(Direcao.NORTE);
				// seta que esta movendo-se
				tank.setMovendo(true);
			} else {
				// seta que esta parado
				tank.setMovendo(false);
			}
			break;
		}

		}
		// caso o tank estiver movendo-se monta a mensagem
		return tank.isMovendo() ? montarMenssagem(tank, null) : null;
	}

	/**
	 * Método que monta a mensagem do tiro para que possa ser enviada ao
	 * servidor.
	 * 
	 * @author Yuri Fialho
	 * @return retorna a mensagem gerada com as informações de tiro.
	 * @return null se alguém já tiver atirado.
	 */
	public Menssagem atirarRemoto(TipoUsuario player) {
		// verifica se existe somente um tiro por usuário
		if ((player == TipoUsuario.A && fireA == null)
				|| (player == TipoUsuario.B && fireB == null)) {
			FireVO fire;
			// inicia um novo tiro pelo usuário que esta solicitando
			if (player == TipoUsuario.A) {
				fireA = new FireSprite(tankA, 0, 0);
				fire = new FireVO(fireA);
			} else {
				fireB = new FireSprite(tankB, 0, 0);
				fire = new FireVO(fireB);
			}
			Direcao direcao = fire.getDirecao();
			fire.setMovendo(false);
			// verifica a direção do tiro para que possa ser direcionado o tiro
			switch (direcao) {
			// acima
			case NORTE: {
				// verifica se esta no limite da tela
				if (fire.getPosY() > Constantes.Visao.LIMITE_TELA_SUPERIOR) {
					// seta que esta movendo-se
					fire.setMovendo(true);
				}
				break;
			}
				// abaixo
			case SUL: {
				// verifica se esta no limite da tela
				if (fire.getPosY() < Constantes.Visao.LIMITE_TELA_INFERIOR) {
					// seta que esta movendo-se
					fire.setMovendo(true);
				}
				break;
			}
				// direita
			case LESTE: {
				// verifica se esta no limite da tela
				if (fire.getPosX() < Constantes.Visao.LIMITE_TELA_DIREITA) {
					// seta que esta movendo-se
					fire.setMovendo(true);
				}
				break;
			}
				// esquerda
			case OESTE: {
				// verifica se esta no limite da tela
				if (fire.getPosX() > Constantes.Visao.LIMITE_TELA_ESQUERDA) {
					// seta que esta movendo-se
					fire.setMovendo(true);
				}
				break;
			}
			}
			// caso estiver movendo-se monta a mensagem de tiro caso não retorna
			// nulo
			return fire.isMovendo() ? montarMenssagem(null, fire) : null;
		} else {
			// não for possivel movimentar
			return null;
		}
	}

	/**
	 * Método que faz o recebimento das mensagens de movimentação vinda do
	 * servidor para que possa-se atualizar a tela do usuário
	 */
	public void run() {
		ComunicaoService service = ComunicaoService.getInstance();
		//pega do seviço de comunicação o stream de entrada
		ObjectInputStream read = service.getReader();
		Menssagem msg;
		try {
			//aguarda a chegada de uma mensagem que seja diferente de null
			while ((msg = (Menssagem) read.readObject()) != null) {
				//verifica se a mesagem refere-se a movimentação do tank
				if (msg.getTank() != null) {
					//movimenta o tank
					andar(msg.getTank().getDirecao(), msg.getTipoUsuario());
				} else {
					//atira 
					fire(msg.getTipoUsuario());
				}

			}
		} catch (Exception e) {
			System.out.println("#ERROR --- run() GamePanel");
			e.printStackTrace();
		}
	}

	/**
	 * Método que faz a verificação se o alguns dos tanks foram atingido pelos
	 * tiros do adversário, verificando se o usuário encontra-se na mesma
	 * posição do tiro do adversário
	 * 
	 * @author Yuri Fialho
	 */
	private void hitPlayer() {
		//posição do tiro usuario A
		int posXFire_A = 0;
		int posYFire_A = 0;
		//posição do tiro usuário B
		int posXFire_B = 0;
		int posYFire_B = 0;
		
		//verifica se existe tiro para o usuário A
		if (fireA != null) {
			//atribui a posição atual do tiro do tank A
			posXFire_A = fireA.getPosX();
			posYFire_A = fireA.getPosY();
			//verifica se o tiro encontra-se na mesma posição do usuário oposto
			if (posX_B == posXFire_A && posY_B == posYFire_A) {
				//utiliza o seviço de jogo para ferir o adversário
				gameService.hitPlayer(TipoUsuario.B);
			}
		}
		//verifica se existe tiro para o usuário B
		if (fireB != null) {
			//atribui a posição atual do tiro do tank A
			posXFire_B = fireB.getPosX();
			posYFire_B = fireB.getPosY();
			//verifica se o tiro encontra-se na mesma posição do usuário oposto
			if (posX_A == posXFire_B && posY_A == posYFire_B) {
				//utiliza o seviço de jogo para ferir o adversário
				gameService.hitPlayer(TipoUsuario.A);
			}
		}
	}

}
