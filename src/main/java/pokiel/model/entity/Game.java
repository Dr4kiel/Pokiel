package pokiel.model.entity;

import java.util.LinkedList;
import java.util.List;

import pokiel.model.PlayerModelInterface;

public class Game {
	
	private CardDeck deck;
	
	private PlayerModelInterface playerModel;
	
	private List<Card> globalHand;
	
	public Game() {
		deck = new CardDeck();
		globalHand = new LinkedList<>();
		
		deck.shuffle();
	}
	
	public void distribuer() {
		for(Player player : playerModel.getPlayerList()) {
			player.addToHand(deck.takeACard()); // card 1
		}
		for(Player player : playerModel.getPlayerList()) {
			player.addToHand(deck.takeACard()); // card 2
		}
		
		for(int i = 0; i < 5; i++) {
			globalHand.add(deck.takeACard());
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
		return globalHand;
	}

	public void setGlobalHand(List<Card> globalHand) {
		this.globalHand = globalHand;
	}
	
}
