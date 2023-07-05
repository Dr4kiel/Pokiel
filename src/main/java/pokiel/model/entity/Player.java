package pokiel.model.entity;

import java.util.ArrayDeque;
import java.util.Deque;

public class Player {
	
	private Deque<Card> hand;
	private int bank;
	private final String name;
	private boolean stillPlaying;
	
	public Player() {
		this("MERP");
	}

	public Player(String name) {
		super();
		this.name = name;
		this.hand = new ArrayDeque<>();
		this.bank = 0;
		this.stillPlaying = true;
	}
	
	public void clearHand() {
		hand.clear();
	}
	
	public void addToHand(Card card) {
		hand.add(card);
	}
	
	
	/* GETTERS AND SETTERS */
	
	public int getBank() {
		return bank;
	}
	
	public Deque<Card> getHand() {
		return hand;
	}
	
	public String getName() {
		return name;
	}
	
	public void setBank(int bank) {
		this.bank = bank;
	}
	
	public void setHand(Deque<Card> hand) {
		this.hand = hand;
	}
	
	public boolean isStillPlaying() {
		return stillPlaying;
	}
	
	public void setStillPlaying(boolean stillPlaying) {
		this.stillPlaying = stillPlaying;
	}

}
