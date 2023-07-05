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
		
		int valueOfHand = checkVictoryConditionForSameCards(counter);
		
		valueOfHand = checkVictoryConditionForDifferentCards(hand, valueOfHand);
		
		if(valueOfHand == 0)
			valueOfHand = valueOfHand | VictoryCondition.HAUTEUR.getValue();
		
		return valueOfHand;
	}

	private static int checkVictoryConditionForDifferentCards(List<Card> hand, int valueOfHand) {
		if(isCouleur(hand))
			valueOfHand = valueOfHand | VictoryCondition.COULEUR.getValue();
		
		if(isSuite(hand))
			valueOfHand = valueOfHand | VictoryCondition.SUITE.getValue();
		
		if(VictoryCondition.COULEUR.arePresent(valueOfHand) && VictoryCondition.SUITE.arePresent(valueOfHand)) {
			valueOfHand = valueOfHand & ~VictoryCondition.COULEUR.getValue();
			valueOfHand = valueOfHand & ~VictoryCondition.SUITE.getValue();
			valueOfHand = valueOfHand | VictoryCondition.QUITE_FLUSH.getValue();
		}
		
		if(VictoryCondition.QUITE_FLUSH.arePresent(valueOfHand) && isQuiteFlushRoyale(hand)) {
				valueOfHand = valueOfHand & ~VictoryCondition.QUITE_FLUSH.getValue();
				valueOfHand = valueOfHand | VictoryCondition.QUINTE_FLUSH_ROYALE.getValue();
		}
		return valueOfHand;
	}

	private static int checkVictoryConditionForSameCards(Map<CardValue, Integer> counter) {
		int valueOfHand = 0;
		
		for(Integer nbOfCard : counter.values()) {
			
			switch(nbOfCard) {
			case 4:
				valueOfHand  = valueOfHand | VictoryCondition.CARRE.getValue();
				break;
			case 3:
				valueOfHand = valueOfHand | VictoryCondition.BRELAN.getValue();
				break;
			case 2:
				if(VictoryCondition.PAIRE.arePresent(valueOfHand)) {
					valueOfHand = valueOfHand | VictoryCondition.DOUBLE_PAIRE.getValue();
					valueOfHand = valueOfHand & ~VictoryCondition.PAIRE.getValue();
				}else
					valueOfHand = valueOfHand | VictoryCondition.PAIRE.getValue();
				break;
			default:
				break;
			}

			if(VictoryCondition.BRELAN.arePresent(valueOfHand) && VictoryCondition.PAIRE.arePresent(valueOfHand)) {
				valueOfHand = valueOfHand | VictoryCondition.FULL.getValue();
				valueOfHand = valueOfHand & ~VictoryCondition.BRELAN.getValue();
				valueOfHand = valueOfHand & ~VictoryCondition.PAIRE.getValue();
			}
		}
		return valueOfHand;
	}
	
	private static boolean isQuiteFlushRoyale(List<Card> hand) {
		
		Collections.sort(hand);
		
		int cardValueCheck = CardValue.AS.getValeur();
		
		for(Card card : hand) {
			if(card.getValue().getValeur() == cardValueCheck) {
				cardValueCheck--;
				if(cardValueCheck == 9)
					break;
			}
		}
		
		return cardValueCheck == CardValue.NEUF.getValeur();
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
		
		if(hand.get(hand.size() % 5 + 1).getValue().getValeur() == 5 && hand.get(0).getValue().compareTo(CardValue.AS) == 0 && ( 5 - hand.get(hand.size() % 5 + 4).getValue().getValeur() == 3))
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