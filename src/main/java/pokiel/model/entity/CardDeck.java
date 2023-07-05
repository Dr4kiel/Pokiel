package pokiel.model.entity;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class CardDeck {
	
	private Deque<Card> deck;
	
	private final Random ran;
	
	public CardDeck() {
		
		deck = new ArrayDeque<>();
		
		for(CardColor color : CardColor.values()) {
			for(CardValue value : CardValue.values())
				deck.add(new Card(value, color));
		}
		
		ran = new Random();
	}
	
	public void shuffle() {
		Deque<Card> newDeck = new ArrayDeque<>();
		
		while(!deck.isEmpty()) {
			int index = ran.nextInt(deck.size());
			Card cardToPick = (Card) deck.toArray()[index];
			newDeck.push(cardToPick);
			deck.remove(cardToPick);
		}
		deck = newDeck;
	}
	
	public Card takeACard() {
		return deck.pop();
	}
	
	public int sizeOfDeck() {
		return deck.size();
	}
	
	public Deque<Card> getDeck(){
		return deck;
	}

}
