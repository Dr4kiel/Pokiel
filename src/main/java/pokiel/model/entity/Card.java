package pokiel.model.entity;

public class Card implements Comparable<Card> {

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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			return value.compareTo(((Card) obj).getValue()) == 0 && color.equals(((Card) obj).getColor());
		} else
			return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "(" + value + "|" + color + ")";
	}

}
