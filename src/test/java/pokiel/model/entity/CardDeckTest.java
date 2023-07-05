package pokiel.model.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardDeckTest {
	
	private CardDeck deck;

	@BeforeEach
	void init() {
		deck = new CardDeck();
	}

	@Test
	void deckCreation64Cards(){
		assertEquals(52, deck.sizeOfDeck());
	}
	
	@Test
	void shuffleTest() {
		Card firstCard = deck.getDeck().pop();
		deck.shuffle();
		
		assertFalse(firstCard.getColor() == deck.getDeck().peekFirst().getColor()
				&& firstCard.getValue() == deck.getDeck().peekFirst().getValue());
	}
	
	@Test
	void takeACardTest() {
		deck.takeACard();
		assertNotEquals(52, deck.sizeOfDeck());
	}

}
