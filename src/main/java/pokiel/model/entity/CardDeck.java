package pokiel.model.entity;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
	
	private Stack<Card> deck;
	
	public CardDeck() {
		
		deck = new Stack<Card>();
		
		for(CardColor color : CardColor.values()) {
			for(CardValue value : CardValue.values())
				deck.add(new Card(value, color));
		}
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public Card takeACard() {
		return deck.pop();
	}
	
	public int sizeOfDeck() {
		return deck.size();
	}
	
	public Stack<Card> getDeck(){
		return deck;
	}

}
