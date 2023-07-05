package pokiel.model.entity;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardCalculator {
	
	public static int checkVictoryCondition(List<Card> hand) {
		
		Collections.sort(hand);
		
		Map<CardValue, Integer> counter = countCards(hand);
		
		int valueOfHand = 0;
		
		for(Integer nbOfCard : counter.values()) {
			if(nbOfCard == 4) {
				valueOfHand = valueOfHand | VictoryCondition.Carré.getValue();
			}
			if(nbOfCard == 3) {
				valueOfHand = valueOfHand | VictoryCondition.Brelan.getValue();
			}
			if(nbOfCard == 2) {
				if(VictoryCondition.Paire.arePresent(valueOfHand)) {
					valueOfHand = valueOfHand | VictoryCondition.DoublePaire.getValue();
					valueOfHand = valueOfHand & ~VictoryCondition.Paire.getValue();
				}else
					valueOfHand = valueOfHand | VictoryCondition.Paire.getValue();
			}
			if(VictoryCondition.Brelan.arePresent(valueOfHand) && VictoryCondition.Paire.arePresent(valueOfHand)) {
				valueOfHand = valueOfHand | VictoryCondition.Full.getValue();
				valueOfHand = valueOfHand & ~VictoryCondition.Brelan.getValue();
				valueOfHand = valueOfHand & ~VictoryCondition.Paire.getValue();
			}
		}
		
		if(isCouleur(hand))
			valueOfHand = valueOfHand | VictoryCondition.Couleur.getValue();
		
		if(isSuite(hand))
			valueOfHand = valueOfHand | VictoryCondition.Suite.getValue();
		
		if(VictoryCondition.Couleur.arePresent(valueOfHand) && VictoryCondition.Suite.arePresent(valueOfHand)) {
			valueOfHand = valueOfHand & ~VictoryCondition.Couleur.getValue();
			valueOfHand = valueOfHand & ~VictoryCondition.Suite.getValue();
			valueOfHand = valueOfHand | VictoryCondition.QuinteFlush.getValue();
		}
		
		if(VictoryCondition.QuinteFlush.arePresent(valueOfHand)) {
			if(isQuiteFlushRoyale(hand)) {
				valueOfHand = valueOfHand & ~VictoryCondition.QuinteFlush.getValue();
				valueOfHand = valueOfHand | VictoryCondition.QuinteFlushRoyale.getValue();
			}
		}
		
		if(valueOfHand == 0)
			valueOfHand = valueOfHand | VictoryCondition.Hauteur.getValue();
		
		return valueOfHand;
	}
	
	private static boolean isQuiteFlushRoyale(List<Card> hand) {
		
		int cardValueCheck = CardValue.As.getValeur();
		
		for(Card card : hand) {
			if(card.getValue().getValeur() == cardValueCheck) {
				cardValueCheck--;
			}
		}
		
		return cardValueCheck == CardValue.Neuf.getValeur();
	}

	private static boolean isSuite(List<Card> hand) {
		
		int compteurSuite = 0;
		
		CardValue prevCardValue = null;
		
		Collections.sort(hand);
		
		for(Card card : hand) {
			if(prevCardValue != null) {
				if(prevCardValue.getValeur() - 1 == card.getValue().getValeur()) {
					compteurSuite++;
				}
			}
			prevCardValue = card.getValue();
		}
		
		return  compteurSuite >= 5;
	}

	private static boolean isCouleur(List<Card> hand) {
		
		Map<CardColor, Integer> nbCouleurs = new HashMap<>();
		
		for(Card card : hand) {
			nbCouleurs.merge(card.getColor(), 1, (prev, one) -> prev + one);
		}
		
		for(Integer nb : nbCouleurs.values()) {
			if(nb >= 5) {
				return true;
			}
		}
		
		return false;
	}

	public static Map<CardValue, Integer> countCards(List<Card> hand){
		
		Map<CardValue, Integer> counter = new HashMap<>();
		
		for(Card card : hand) {
			counter.merge(card.getValue(), 1, (prev, one) -> prev + one);
		}
		
		return counter;
	}
}