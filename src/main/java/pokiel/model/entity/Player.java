package pokiel.model.entity;

import java.util.List;

public class Player {

	private PlayerHand hand;
	private int bank;
	private final String name;
	private boolean stillPlaying;

	public Player() {
		this("MERP");
	}

	public Player(String name) {
		super();
		this.name = name;
		hand = new PlayerHand();
		this.bank = 0;
		this.stillPlaying = true;
	}

	public void clearHand() {
		hand.getHand().clear();
	}

	public void addToHand(Card card) {
		hand.getHand().add(card);
	}

	/* GETTERS AND SETTERS */

	public int getBank() {
		return bank;
	}

	public PlayerHand getPlayerHand() {
		return hand;
	}

	public List<Card> getHand() {
		return hand.getHand();
	}

	public String getName() {
		return name;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}

	public void setPlayerHand(PlayerHand hand) {
		this.hand = hand;
	}

	public boolean isStillPlaying() {
		return stillPlaying;
	}

	public void setStillPlaying(boolean stillPlaying) {
		this.stillPlaying = stillPlaying;
	}

	public String printHand() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (Card card : hand.getHand()) {
			sb.append(card.toString());
			sb.append(" ");
		}
		sb.append("]");
		return sb.toString();
	}

}
