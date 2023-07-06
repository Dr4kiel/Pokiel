package pokiel.model.entity;

import java.util.LinkedList;
import java.util.List;

public class Hand {

	protected List<Card> hand;

	public Hand() {
		super();
		hand = new LinkedList<>();
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

}