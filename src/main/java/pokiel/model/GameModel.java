package pokiel.model;

import java.util.Collections;
import java.util.Map;

import pokiel.model.entity.Game;
import pokiel.model.entity.Player;
import pokiel.model.entity.VictoryCondition;
import pokiel.model.exception.NoPlayerInTheModelException;
import pokiel.model.exception.UnimplementedEquality;

public class GameModel {

	private Game game;

	private final PlayerModel playerModel;

	public GameModel(PlayerModel playerModel) {
		super();
		this.playerModel = playerModel;
	}

	public void nouvellePartie() throws NoPlayerInTheModelException, UnimplementedEquality {
		game = new Game();

		game.setPlayerModel(playerModel);

		Map<Player, Integer> resultPlayers = game.lancerPartie();

		for (Player player : playerModel.getPlayerList()) {
			System.out.println(player.getName() + " : " + player.printHand());
		}

		Player vainqueur = trouverVainqueur(resultPlayers);

		System.out.println("Vainqueur : " + vainqueur.getName() + " | Victoire par "
				+ VictoryCondition.getTheVictoryCondition(resultPlayers.get(vainqueur)));

		Collections.sort(game.getGlobalHand());

		System.out.println("" + vainqueur.getHand() + game.getGlobalHand());
	}

	private Player trouverVainqueur(Map<Player, Integer> resultPlayers)
			throws NoPlayerInTheModelException, UnimplementedEquality {

		Player vainqueur = Collections.max(resultPlayers.entrySet(), Map.Entry.comparingByValue()).getKey();

		for (Player player : playerModel.getPlayerList()) {
			if (resultPlayers.get(vainqueur).equals(resultPlayers.get(player)) && !player.equals(vainqueur)) {
				if (Integer.compare(vainqueur.getPlayerHand().getValueOfTheHand(),
						player.getPlayerHand().getValueOfTheHand()) == 0)
					System.out.println("Egalité avec : " + player.getName());
				else if (Integer.compare(vainqueur.getPlayerHand().getValueOfTheHand(),
						player.getPlayerHand().getValueOfTheHand()) < 0)
					vainqueur = player;
			}

		}

		if (vainqueur == null)
			throw new NoPlayerInTheModelException();

		return vainqueur;
	}

}
