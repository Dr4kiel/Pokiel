package pokiel.model.entity;

public class PlayerHand extends Hand implements Comparable<PlayerHand> {
	private int valueOfTheHand;

	public PlayerHand() {
		super();
		valueOfTheHand = 0;
	}

	public int getValueOfTheHand() {
		return valueOfTheHand;
	}

	public void setValueOfTheHand(int valueOfTheHand) {
		this.valueOfTheHand = valueOfTheHand;
	}

	@Override
	public int compareTo(PlayerHand otherHand) {
		return Integer.compare(valueOfTheHand, otherHand.getValueOfTheHand());
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof PlayerHand && valueOfTheHand == ((PlayerHand) obj).getValueOfTheHand();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}