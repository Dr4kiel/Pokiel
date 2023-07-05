package pokiel.model.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardCalculatorTest {
	
	private List<Card> hand;

	@BeforeEach
	void init() {
		hand = new Stack<Card>();
		
		hand.add(new Card(CardValue.Cinq, CardColor.Coeur));
		hand.add(new Card(CardValue.Deux, CardColor.Pique));
		hand.add(new Card(CardValue.As, CardColor.Coeur));
		hand.add(new Card(CardValue.Dame, CardColor.Carreau));
		hand.add(new Card(CardValue.Sept, CardColor.Pique));
		hand.add(new Card(CardValue.Huit, CardColor.Pique));
		hand.add(new Card(CardValue.Cinq, CardColor.Trèfle));
	}

	@Test
	void testSorting() {
		
		Collections.sort(hand);
		
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for(Card c : hand) {
			sb.append(c.getValue().toString() + " ");
		}
		sb.append("]");
		
		assertEquals("[ As Dame Huit Sept Cinq Cinq Deux ]", sb.toString());
	}
	
	@Test
	void evaluerPaire() {
		assertTrue(VictoryCondition.Paire.arePresent(CardCalculator.checkVictoryCondition(hand)));
	}

}
