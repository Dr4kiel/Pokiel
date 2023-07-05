package pokiel.model.entity;

public class Card implements Comparable<Card>{
	
	private final CardValue value;
	
	private final CardColor color;

	public Card(CardValue value, CardColor color) {
		super();
		this.value = value;
		this.color = color;
	}

	public CardValue getValue() {
		return value;
	}

	public CardColor getColor() {
		return color;
	}

	@Override
	public int compareTo(Card card) {
		return Integer.compare(card.getValue().getValeur(), getValue().getValeur());
	}

}
