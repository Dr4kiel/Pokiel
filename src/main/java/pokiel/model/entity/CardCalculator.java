package pokiel.model.entity;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CardCalculator {
	
	private CardCalculator() {
		
	}
	
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
		
		if(VictoryCondition.QuinteFlush.arePresent(valueOfHand) && isQuiteFlushRoyale(hand)) {
				valueOfHand = valueOfHand & ~VictoryCondition.QuinteFlush.getValue();
				valueOfHand = valueOfHand | VictoryCondition.QuinteFlushRoyale.getValue();
		}
		
		if(valueOfHand == 0)
			valueOfHand = valueOfHand | VictoryCondition.Hauteur.getValue();
		
		return valueOfHand;
	}
	
	private static boolean isQuiteFlushRoyale(List<Card> hand) {
		
		Collections.sort(hand);
		
		int cardValueCheck = CardValue.As.getValeur();
		
		for(Card card : hand) {
			if(card.getValue().getValeur() == cardValueCheck) {
				cardValueCheck--;
				if(cardValueCheck == 9)
					break;
			}
		}
		
		return cardValueCheck == CardValue.Neuf.getValeur();
	}

	private static boolean isSuite(List<Card> hand) {
		
		boolean result = false;
		
		Collections.sort(hand);
		
		for(int i = 0; i <= hand.size()%5; i++) {
			int valeur = hand.get(i).getValue().getValeur();
			int count = 0;
			for(int j = i; j <= i+4; j++) {
				if(valeur == hand.get(j).getValue().getValeur())
					count++;
				valeur--;
			}
			if(count == 5)
				result = true;
		}
		
		if(hand.get(hand.size() % 5 + 1).getValue().getValeur() == 5 && hand.get(0).getValue().compareTo(CardValue.As) == 0 && ( 5 - hand.get(hand.size() % 5 + 4).getValue().getValeur() == 3))
			result = true;
		
		return result;
	}

	private static boolean isCouleur(List<Card> hand) {
		
		Map<CardColor, Integer> nbCouleurs = new EnumMap<>(CardColor.class);
		
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
		
		Map<CardValue, Integer> counter = new EnumMap<>(CardValue.class);
		
		for(Card card : hand) {
			counter.merge(card.getValue(), 1, (prev, one) -> prev + one);
		}
		
		return counter;
	}
}