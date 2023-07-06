package pokiel.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardCalculatorTest {

	private PlayerHand hand;

	@BeforeEach
	void init() {
		hand = new PlayerHand();

		hand.getHand().add(new Card(CardValue.CINQ, CardColor.COEUR));
		hand.getHand().add(new Card(CardValue.DEUX, CardColor.PIQUE));
		hand.getHand().add(new Card(CardValue.AS, CardColor.COEUR));
		hand.getHand().add(new Card(CardValue.DAME, CardColor.CARREAU));
		hand.getHand().add(new Card(CardValue.SEPT, CardColor.PIQUE));
		hand.getHand().add(new Card(CardValue.HUIT, CardColor.PIQUE));
		hand.getHand().add(new Card(CardValue.CINQ, CardColor.TREFLE));
	}

	@Test
	void testSorting() {

		Collections.sort(hand.getHand());

		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (Card c : hand.getHand()) {
			sb.append(c.getValue().toString() + " ");
		}
		sb.append("]");

		assertEquals("[ AS DAME HUIT SEPT CINQ CINQ DEUX ]", sb.toString());
	}

	@Test
	void evaluerPaire() {
		assertTrue(VictoryCondition.PAIRE.arePresent(CardCalculator.checkVictoryCondition(hand)));
	}

	@Test
	void evaluerDoublePaire() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.AS, CardColor.CARREAU));
		deck.add(new Card(CardValue.AS, CardColor.PIQUE));
		deck.add(new Card(CardValue.CINQ, CardColor.TREFLE));
		deck.add(new Card(CardValue.CINQ, CardColor.COEUR));
		deck.add(new Card(CardValue.DAME, CardColor.PIQUE));
		deck.add(new Card(CardValue.NEUF, CardColor.PIQUE));
		deck.add(new Card(CardValue.DEUX, CardColor.CARREAU));

		hand.setHand(deck);

		assertTrue(VictoryCondition.DOUBLE_PAIRE.arePresent(CardCalculator.checkVictoryCondition(hand)));
	}

	@Test
	void evaluerBrelan() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.AS, CardColor.CARREAU));
		deck.add(new Card(CardValue.AS, CardColor.PIQUE));
		deck.add(new Card(CardValue.AS, CardColor.TREFLE));
		deck.add(new Card(CardValue.CINQ, CardColor.COEUR));
		deck.add(new Card(CardValue.DAME, CardColor.PIQUE));
		deck.add(new Card(CardValue.NEUF, CardColor.PIQUE));
		deck.add(new Card(CardValue.DEUX, CardColor.CARREAU));

		hand.setHand(deck);

		assertTrue(VictoryCondition.BRELAN.arePresent(CardCalculator.checkVictoryCondition(hand)));
	}

	@Test
	void evaluerSuite() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.AS, CardColor.CARREAU));
		deck.add(new Card(CardValue.TROIS, CardColor.PIQUE));
		deck.add(new Card(CardValue.QUATRE, CardColor.TREFLE));
		deck.add(new Card(CardValue.CINQ, CardColor.COEUR));
		deck.add(new Card(CardValue.DAME, CardColor.PIQUE));
		deck.add(new Card(CardValue.NEUF, CardColor.PIQUE));
		deck.add(new Card(CardValue.DEUX, CardColor.CARREAU));

		hand.setHand(deck);

		assertTrue(VictoryCondition.SUITE.arePresent(CardCalculator.checkVictoryCondition(hand)));
	}

	@Test
	void evaluerCouleur() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.AS, CardColor.CARREAU));
		deck.add(new Card(CardValue.HUIT, CardColor.CARREAU));
		deck.add(new Card(CardValue.ROI, CardColor.CARREAU));
		deck.add(new Card(CardValue.CINQ, CardColor.CARREAU));
		deck.add(new Card(CardValue.DAME, CardColor.PIQUE));
		deck.add(new Card(CardValue.NEUF, CardColor.PIQUE));
		deck.add(new Card(CardValue.DEUX, CardColor.CARREAU));

		hand.setHand(deck);

		assertTrue(VictoryCondition.COULEUR.arePresent(CardCalculator.checkVictoryCondition(hand)));
	}

	@Test
	void evaluerFull() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.AS, CardColor.CARREAU));
		deck.add(new Card(CardValue.AS, CardColor.PIQUE));
		deck.add(new Card(CardValue.AS, CardColor.TREFLE));
		deck.add(new Card(CardValue.CINQ, CardColor.COEUR));
		deck.add(new Card(CardValue.DAME, CardColor.PIQUE));
		deck.add(new Card(CardValue.DEUX, CardColor.PIQUE));
		deck.add(new Card(CardValue.DEUX, CardColor.CARREAU));

		hand.setHand(deck);

		assertTrue(VictoryCondition.FULL.arePresent(CardCalculator.checkVictoryCondition(hand)));
	}

	@Test
	void evaluerCarre() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.AS, CardColor.CARREAU));
		deck.add(new Card(CardValue.AS, CardColor.PIQUE));
		deck.add(new Card(CardValue.AS, CardColor.TREFLE));
		deck.add(new Card(CardValue.AS, CardColor.COEUR));
		deck.add(new Card(CardValue.DAME, CardColor.PIQUE));
		deck.add(new Card(CardValue.DEUX, CardColor.PIQUE));
		deck.add(new Card(CardValue.CINQ, CardColor.CARREAU));

		hand.setHand(deck);

		assertTrue(VictoryCondition.CARRE.arePresent(CardCalculator.checkVictoryCondition(hand)));
	}

	@Test
	void evaluerQuinteFlush() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.SIX, CardColor.CARREAU));
		deck.add(new Card(CardValue.TROIS, CardColor.CARREAU));
		deck.add(new Card(CardValue.QUATRE, CardColor.CARREAU));
		deck.add(new Card(CardValue.CINQ, CardColor.CARREAU));
		deck.add(new Card(CardValue.DAME, CardColor.PIQUE));
		deck.add(new Card(CardValue.NEUF, CardColor.PIQUE));
		deck.add(new Card(CardValue.DEUX, CardColor.CARREAU));

		hand.setHand(deck);

		assertTrue(VictoryCondition.QUINTE_FLUSH.arePresent(CardCalculator.checkVictoryCondition(hand)));
	}

	@Test
	void evaluerQuinteFlushRoyale() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.AS, CardColor.CARREAU));
		deck.add(new Card(CardValue.DIX, CardColor.CARREAU));
		deck.add(new Card(CardValue.VALET, CardColor.CARREAU));
		deck.add(new Card(CardValue.ROI, CardColor.CARREAU));
		deck.add(new Card(CardValue.DAME, CardColor.CARREAU));
		deck.add(new Card(CardValue.NEUF, CardColor.PIQUE));
		deck.add(new Card(CardValue.DEUX, CardColor.COEUR));

		hand.setHand(deck);

		assertTrue(VictoryCondition.QUINTE_FLUSH_ROYALE.arePresent(CardCalculator.checkVictoryCondition(hand)));
	}

	@Test
	void evaluerPresqueSuite() {
		List<Card> deck = new Stack<>();
		deck.add(new Card(CardValue.AS, CardColor.TREFLE));
		deck.add(new Card(CardValue.DIX, CardColor.CARREAU));
		deck.add(new Card(CardValue.ROI, CardColor.CARREAU));
		deck.add(new Card(CardValue.ROI, CardColor.CARREAU));
		deck.add(new Card(CardValue.DAME, CardColor.CARREAU));
		deck.add(new Card(CardValue.NEUF, CardColor.PIQUE));
		deck.add(new Card(CardValue.DEUX, CardColor.COEUR));

		hand.setHand(deck);

		assertFalse(VictoryCondition.SUITE.arePresent(CardCalculator.checkVictoryCondition(hand)));
	}

}
