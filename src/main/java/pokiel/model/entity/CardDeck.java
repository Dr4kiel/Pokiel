package pokiel.model.entity;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CardDeck {
	
	private Deque<Card> deck;
	
	private final Random ran;
	
	public CardDeck() {
		
		deck = new LinkedList<>();
		
		for(CardColor color : CardColor.values()) {
			for(CardValue value : CardValue.values())
				deck.add(new Card(value, color));
		}
		
		ran = new Random();
	}
	
	public void shuffle() {
		if(deck instanceof List)
			Collections.shuffle((List<Card>) deck);
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
