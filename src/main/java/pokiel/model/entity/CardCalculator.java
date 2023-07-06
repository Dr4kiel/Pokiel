package pokiel.model.entity;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CardCalculator {

	private CardCalculator() {

	}

	public static int checkVictoryCondition(PlayerHand hand) {

		Collections.sort(hand.getHand());

		Map<CardValue, Integer> counter = countCards(hand.getHand());

		int valueOfHand = checkVictoryConditionForSameCards(counter);

		if (valueOfHand != 0)
			hand.setValueOfTheHand(
					Collections.max(counter.entrySet(), Map.Entry.comparingByValue()).getKey().getValeur());

		valueOfHand = checkVictoryConditionForDifferentCards(hand, valueOfHand);

		valueOfHand = valueOfHand | VictoryCondition.HAUTEUR.getValue();

//		System.out.println(hand.getValueOfTheHand());

		// Max et Min sont inversées pour des facilités de calculs
		hand.setValueOfTheHand(hand.getValueOfTheHand() + Collections.min(hand.getHand()).getValue().getValeur());

//		System.out.println(hand.getValueOfTheHand());

		hand.setValueOfTheHand(hand.getValueOfTheHand() << 10);
		hand.setValueOfTheHand(hand.getValueOfTheHand() | valueOfHand);

		System.out.println(hand.getValueOfTheHand());

		return valueOfHand;
	}

	private static int checkVictoryConditionForDifferentCards(PlayerHand hand, int valueOfHand) {
		if (isCouleur(hand.getHand()))
			valueOfHand = valueOfHand | VictoryCondition.COULEUR.getValue();

		if (isSuite(hand))
			valueOfHand = valueOfHand | VictoryCondition.SUITE.getValue();

		if (VictoryCondition.COULEUR.arePresent(valueOfHand) && VictoryCondition.SUITE.arePresent(valueOfHand)) {
			valueOfHand = valueOfHand & ~VictoryCondition.COULEUR.getValue();
			valueOfHand = valueOfHand & ~VictoryCondition.SUITE.getValue();
			valueOfHand = valueOfHand | VictoryCondition.QUINTE_FLUSH.getValue();
		}

		if (VictoryCondition.QUINTE_FLUSH.arePresent(valueOfHand) && isQuinteFlushRoyale(hand.getHand())) {
			valueOfHand = valueOfHand & ~VictoryCondition.QUINTE_FLUSH.getValue();
			valueOfHand = valueOfHand | VictoryCondition.QUINTE_FLUSH_ROYALE.getValue();
		}
		return valueOfHand;
	}

	private static int checkVictoryConditionForSameCards(Map<CardValue, Integer> counter) {
		int valueOfHand = 0;

		for (Integer nbOfCard : counter.values()) {

			switch (nbOfCard) {
			case 4:
				valueOfHand = valueOfHand | VictoryCondition.CARRE.getValue();
				break;
			case 3:
				valueOfHand = valueOfHand | VictoryCondition.BRELAN.getValue();
				break;
			case 2:
				if (VictoryCondition.PAIRE.arePresent(valueOfHand)) {
					valueOfHand = valueOfHand | VictoryCondition.DOUBLE_PAIRE.getValue();
					valueOfHand = valueOfHand & ~VictoryCondition.PAIRE.getValue();
				} else
					valueOfHand = valueOfHand | VictoryCondition.PAIRE.getValue();
				break;
			default:
				break;
			}

			if (VictoryCondition.BRELAN.arePresent(valueOfHand) && VictoryCondition.PAIRE.arePresent(valueOfHand)) {
				valueOfHand = valueOfHand | VictoryCondition.FULL.getValue();
				valueOfHand = valueOfHand & ~VictoryCondition.BRELAN.getValue();
				valueOfHand = valueOfHand & ~VictoryCondition.PAIRE.getValue();
			}
		}
		return valueOfHand;
	}

	private static boolean isQuinteFlushRoyale(List<Card> hand) {

		Collections.sort(hand);

		int cardValueCheck = CardValue.AS.getValeur();

		for (Card card : hand) {
			if (card.getValue().getValeur() == cardValueCheck) {
				cardValueCheck--;
				if (cardValueCheck == 9)
					break;
			}
		}

		return cardValueCheck == CardValue.NEUF.getValeur();
	}

	private static boolean isSuite(PlayerHand hand) {

		boolean result = false;

		Collections.sort(hand.getHand());

		for (int i = 0; i <= hand.getHand().size() % 5; i++) {
			int valeur = hand.getHand().get(i).getValue().getValeur();
			int count = 0;
			for (int j = i; j <= i + 4; j++) {
				if (valeur == hand.getHand().get(j).getValue().getValeur())
					count++;
				valeur--;
			}
			if (count == 5) {
				result = true;
				hand.setValueOfTheHand(hand.getHand().get(i).getValue().getValeur());
			}
		}

		if (hand.getHand().get(hand.getHand().size() % 5 + 1).getValue().getValeur() == 5
				&& hand.getHand().get(0).getValue().compareTo(CardValue.AS) == 0
				&& (5 - hand.getHand().get(hand.getHand().size() % 5 + 4).getValue().getValeur() == 3)) {
			result = true;
			hand.setValueOfTheHand(hand.getHand().get(0).getValue().getValeur());
		}

		return result;
	}

	private static boolean isCouleur(List<Card> hand) {

		Map<CardColor, Integer> nbCouleurs = new EnumMap<>(CardColor.class);

		for (Card card : hand) {
			nbCouleurs.merge(card.getColor(), 1, (prev, one) -> prev + one);
		}

		for (Integer nb : nbCouleurs.values()) {
			if (nb >= 5) {
				return true;
			}
		}

		return false;
	}

	public static Map<CardValue, Integer> countCards(List<Card> hand) {

		Map<CardValue, Integer> counter = new EnumMap<>(CardValue.class);

		for (Card card : hand) {
			counter.merge(card.getValue(), 1, (prev, one) -> prev + one);
		}

		return counter;
	}
}