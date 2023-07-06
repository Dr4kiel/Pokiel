package pokiel.model.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pokiel.model.PlayerModelInterface;

public class Game {

	private static final int MAX_CARD_IN_GLOBAL_HAND = 5;

	private CardDeck deck;

	private PlayerModelInterface playerModel;

	private Hand globalHand;

	public Game() {
		deck = new CardDeck();
		globalHand = new Hand();
		deck.shuffle();
	}

	public Map<Player, Integer> lancerPartie() {

		deck.shuffle();

		distribuer();

		Map<Player, Integer> resultPlayers = new HashMap<>();

		for (Player player : playerModel.getPlayerList()) {

			for (Card card : globalHand.getHand()) {
				player.addToHand(card);
			}

			resultPlayers.put(player, CardCalculator.checkVictoryCondition(player.getPlayerHand()));

			player.getHand().removeAll(globalHand.getHand());
		}

		return resultPlayers;
	}

	public void distribuer() {
		for (Player player : playerModel.getPlayerList()) {
			player.addToHand(deck.takeACard()); // card 1
		}
		for (Player player : playerModel.getPlayerList()) {
			player.addToHand(deck.takeACard()); // card 2
		}

		for (int i = 0; i < MAX_CARD_IN_GLOBAL_HAND; i++) {
			globalHand.getHand().add(deck.takeACard());
		}
	}

	public CardDeck getDeck() {
		return deck;
	}

	public PlayerModelInterface getPlayerModel() {
		return playerModel;
	}

	public void setPlayerModel(PlayerModelInterface playerModel) {
		this.playerModel = playerModel;
	}

	public List<Card> getGlobalHand() {
		return globalHand.getHand();
	}

	public void setGlobalHand(List<Card> globalHand) {
		this.globalHand.setHand(globalHand);
	}

}
