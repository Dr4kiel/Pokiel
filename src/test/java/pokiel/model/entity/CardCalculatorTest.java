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
	
	@Test
	void evaluerDoublePaire() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.As, CardColor.Carreau));
		deck.add(new Card(CardValue.As, CardColor.Pique));
		deck.add(new Card(CardValue.Cinq, CardColor.Trèfle));
		deck.add(new Card(CardValue.Cinq, CardColor.Coeur));
		deck.add(new Card(CardValue.Dame, CardColor.Pique));
		deck.add(new Card(CardValue.Neuf, CardColor.Pique));
		deck.add(new Card(CardValue.Deux, CardColor.Carreau));
		
		assertTrue(VictoryCondition.DoublePaire.arePresent(CardCalculator.checkVictoryCondition(deck)));
	}
	
	@Test
	void evaluerBrelan() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.As, CardColor.Carreau));
		deck.add(new Card(CardValue.As, CardColor.Pique));
		deck.add(new Card(CardValue.As, CardColor.Trèfle));
		deck.add(new Card(CardValue.Cinq, CardColor.Coeur));
		deck.add(new Card(CardValue.Dame, CardColor.Pique));
		deck.add(new Card(CardValue.Neuf, CardColor.Pique));
		deck.add(new Card(CardValue.Deux, CardColor.Carreau));
		
		assertTrue(VictoryCondition.Brelan.arePresent(CardCalculator.checkVictoryCondition(deck)));
	}
	
	@Test
	void evaluerSuite() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.As, CardColor.Carreau));
		deck.add(new Card(CardValue.Trois, CardColor.Pique));
		deck.add(new Card(CardValue.Quatre, CardColor.Trèfle));
		deck.add(new Card(CardValue.Cinq, CardColor.Coeur));
		deck.add(new Card(CardValue.Dame, CardColor.Pique));
		deck.add(new Card(CardValue.Neuf, CardColor.Pique));
		deck.add(new Card(CardValue.Deux, CardColor.Carreau));
		
		assertTrue(VictoryCondition.Suite.arePresent(CardCalculator.checkVictoryCondition(deck)));
	}
	
	@Test
	void evaluerCouleur() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.As, CardColor.Carreau));
		deck.add(new Card(CardValue.Huit, CardColor.Carreau));
		deck.add(new Card(CardValue.Roi, CardColor.Carreau));
		deck.add(new Card(CardValue.Cinq, CardColor.Carreau));
		deck.add(new Card(CardValue.Dame, CardColor.Pique));
		deck.add(new Card(CardValue.Neuf, CardColor.Pique));
		deck.add(new Card(CardValue.Deux, CardColor.Carreau));
		
		assertTrue(VictoryCondition.Couleur.arePresent(CardCalculator.checkVictoryCondition(deck)));
	}
	
	@Test
	void evaluerFull() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.As, CardColor.Carreau));
		deck.add(new Card(CardValue.As, CardColor.Pique));
		deck.add(new Card(CardValue.As, CardColor.Trèfle));
		deck.add(new Card(CardValue.Cinq, CardColor.Coeur));
		deck.add(new Card(CardValue.Dame, CardColor.Pique));
		deck.add(new Card(CardValue.Deux, CardColor.Pique));
		deck.add(new Card(CardValue.Deux, CardColor.Carreau));
		
		assertTrue(VictoryCondition.Full.arePresent(CardCalculator.checkVictoryCondition(deck)));
	}
	
	@Test
	void evaluerCarre() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.As, CardColor.Carreau));
		deck.add(new Card(CardValue.As, CardColor.Pique));
		deck.add(new Card(CardValue.As, CardColor.Trèfle));
		deck.add(new Card(CardValue.As, CardColor.Coeur));
		deck.add(new Card(CardValue.Dame, CardColor.Pique));
		deck.add(new Card(CardValue.Deux, CardColor.Pique));
		deck.add(new Card(CardValue.Cinq, CardColor.Carreau));
		
		assertTrue(VictoryCondition.Carré.arePresent(CardCalculator.checkVictoryCondition(deck)));
	}
	
	@Test
	void evaluerQuinteFlush() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.Six, CardColor.Carreau));
		deck.add(new Card(CardValue.Trois, CardColor.Carreau));
		deck.add(new Card(CardValue.Quatre, CardColor.Carreau));
		deck.add(new Card(CardValue.Cinq, CardColor.Carreau));
		deck.add(new Card(CardValue.Dame, CardColor.Pique));
		deck.add(new Card(CardValue.Neuf, CardColor.Pique));
		deck.add(new Card(CardValue.Deux, CardColor.Carreau));
		
		assertTrue(VictoryCondition.QuinteFlush.arePresent(CardCalculator.checkVictoryCondition(deck)));
	}
	
	@Test
	void evaluerQuinteFlushRoyale() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.As, CardColor.Carreau));
		deck.add(new Card(CardValue.Dix, CardColor.Carreau));
		deck.add(new Card(CardValue.Valet, CardColor.Carreau));
		deck.add(new Card(CardValue.Roi, CardColor.Carreau));
		deck.add(new Card(CardValue.Dame, CardColor.Carreau));
		deck.add(new Card(CardValue.Neuf, CardColor.Pique));
		deck.add(new Card(CardValue.Deux, CardColor.Coeur));
		
		assertTrue(VictoryCondition.QuinteFlushRoyale.arePresent(CardCalculator.checkVictoryCondition(deck)));
	}
	
	@Test
	void evaluerPresqueSuite() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.As, CardColor.Trèfle));
		deck.add(new Card(CardValue.Dix, CardColor.Carreau));
		deck.add(new Card(CardValue.Roi, CardColor.Carreau));
		deck.add(new Card(CardValue.Roi, CardColor.Carreau));
		deck.add(new Card(CardValue.Dame, CardColor.Carreau));
		deck.add(new Card(CardValue.Neuf, CardColor.Pique));
		deck.add(new Card(CardValue.Deux, CardColor.Coeur));
		
		assertFalse(VictoryCondition.Suite.arePresent(CardCalculator.checkVictoryCondition(deck)));
	}

}
